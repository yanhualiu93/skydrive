package top.liuyanhua.skydrive.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.liuyanhua.skydrive.common.FileCommonCode;
import top.liuyanhua.skydrive.mapper.FileModelMapper;
import top.liuyanhua.skydrive.models.FileModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileModelService extends ServiceImpl<FileModelMapper, FileModel> {

    @Resource
    private FileModelMapper fileModelMapper;
    private static final String UPLOAD_DIR = "/Users/liuyanhua/IdeaProjects/skydrive/upload";

    public void saveFile(MultipartFile file, String fileIndex, String fileMd5, long fileSize) throws Exception {
        Path filePath = Paths.get(UPLOAD_DIR, fileMd5);
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
        }
        file.transferTo(filePath.resolve(fileIndex));
    }

    public void margeFile(FileModel entity) throws IOException, NoSuchAlgorithmException {
        //MessageDigest digest = MessageDigest.getInstance("MD5");
        String fileName = entity.getFileName();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String md5 = entity.getMd5();
        Path filePath = Paths.get(UPLOAD_DIR, md5);
        File fileFolder = new File(String.valueOf(filePath));
        String[] fileNames = fileFolder.list();
        Arrays.sort(fileNames, (s1, s2) -> {
            return Integer.valueOf(s1) - Integer.valueOf(s2);
        });
        String p = UPLOAD_DIR + "/" + fileName;
        FileChannel fileChannelOut = new FileOutputStream(p).getChannel();
        for (String name : fileNames) {
            FileChannel fileChannelIn = new FileInputStream(UPLOAD_DIR + "/" + md5 +"/"+name).getChannel();
            long size = fileChannelIn.size();
            fileChannelIn.transferTo(0, fileChannelIn.size(), fileChannelOut);
            fileChannelIn.close();
        }
        fileChannelOut.close();
        entity.setDownloadUrl(fileName);
        entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        entity.setStatus(0);
        entity.setStorageMode(FileCommonCode.STORAGE_MODE_LOCAL);
        entity.setSecondUpload(FileCommonCode.SECOND_NO);
        fileModelMapper.insert(entity);
    }
    public void downloadFile(String md5 , String fileName,
                               HttpServletResponse response) {
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            // 设置文件路径
            File file = new File(UPLOAD_DIR, md5+suffixName);
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    // 下载文件能正常显示中文
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}


