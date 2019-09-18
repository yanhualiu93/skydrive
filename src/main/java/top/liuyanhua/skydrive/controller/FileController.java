package top.liuyanhua.skydrive.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.liuyanhua.skydrive.common.Response;
import top.liuyanhua.skydrive.models.FileCacheInfo;
import top.liuyanhua.skydrive.models.FileModel;
import top.liuyanhua.skydrive.models.FileModelExample;
import top.liuyanhua.skydrive.services.FileModelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created with IDEA
 *
 * @author: liuyanhua
 * @date:2019-06-08 15:48
 * @description //TODO $
 */
@RestController
@CrossOrigin(origins = {"*", "/"})
public class FileController {
    private static  final  String PREFIX_KEY = "file:";
    @Resource
    private FileModelService fileModelService;
    @Resource
    private RedisTemplate<String,FileCacheInfo>  redisTemplate;

    /**
     * code 201 上传中 ，202 已经上传 ，203 未上传
     * @param fileMd5
     * @param fileName
     * @return
     */
    @ApiOperation(value = "获取文件是否上传")
    @GetMapping("/isUpload")
    public Response<FileCacheInfo> isUpload(@RequestParam("fileMd5") String fileMd5,
                             @RequestParam("fileName")String fileName){
        FileCacheInfo fileCacheInfo = redisTemplate.opsForValue().get(PREFIX_KEY+fileMd5);
        if (fileCacheInfo !=null){
            return  new Response<FileCacheInfo>().setData(fileCacheInfo).success("上传中").setCode(201);
        }
        QueryWrapper<FileModel> queryWrapper = new QueryWrapper<>();
        // queryWrapper.le("file_name",fileName);
        queryWrapper.le("md5",fileMd5);
        FileModel fileModel = fileModelService.getOne(queryWrapper);
        if (fileModel!=null){
            if ( fileName.equals(fileModel.getFileName())) {
                return new Response().setData(fileModel).success("已经上传").setCode(202);
            }
        }
        return  new Response().setData(new FileCacheInfo()).success().setCode(203);
    }
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Response<FileCacheInfo> upload(
            @ApiParam(value = "上传的文件",required = true)@RequestParam("file")MultipartFile multipartFile,
            @ApiParam(value = "文件总大小",required = true)@RequestParam("fileSize") long fileSize,
            @ApiParam(value = "文件分块索引，0，1，2，3...",required = true)@RequestParam("fileIndex") int fileIndex,
            @ApiParam(value = "文件md5值",required = true)@RequestParam("fileMd5") String fileMd5,
            @ApiParam(value = "文件分块大小",required = true)@RequestParam("chunkSize") long chunkSize,
            @ApiParam(value = "文件名字",required = true)@RequestParam("fileName")String fileName,
            @ApiParam(value = "文件分片总数",required = true)@RequestParam("chunks")int chunks,
            @ApiParam(value = "文件类型",required = true) @RequestParam("fileType") String fileType ){
        try {
            fileModelService.saveFile(multipartFile,String.valueOf(fileIndex), fileMd5,fileSize);
            FileCacheInfo fileCacheInfo = new FileCacheInfo();
            fileCacheInfo.setChunkSize(chunkSize);
            fileCacheInfo.setCurIndex(fileIndex);
            fileCacheInfo.setMd5(fileMd5);
            fileCacheInfo.setFileName(fileName);
            fileCacheInfo.setUploadDate(new Date());
            redisTemplate.opsForValue().set(PREFIX_KEY + fileMd5,fileCacheInfo);
            if (fileIndex == chunks-1 ){
                FileModel file = new FileModel();
                file.setStatus(0);
                file.setFileSize(fileSize);
                file.setFileName(fileName);
                file.setFileType(fileType);
                file.setMd5(fileMd5);
                fileModelService.margeFile(file);
                redisTemplate.delete(PREFIX_KEY + fileMd5);
            }
            return  new Response<FileCacheInfo>().success().setData(fileCacheInfo);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("文件上传失败",e);
            return  new Response<>().setCode(500).setMessage("文件上传失败");
        }
    }
    @ApiOperation(value = "获取文件列表")
    @GetMapping("/list")
    public Response<IPage<FileModel>> list(
            @RequestParam(name="fileName",required = false) String fileName,
            @RequestParam(value = "current" ,required = false) Long current,
            @RequestParam(value = "size",required = false)Long size){
        FileModelExample fileExample = new FileModelExample();
        FileModelExample.Criteria  criteria = fileExample.createCriteria();
        QueryWrapper<FileModel> wrapper = new QueryWrapper<>();

        if (fileName!=null){
            wrapper.like("file_name",fileName);
        }
        IPage<FileModel> page = new Page<>();
        if (current == null){
            current = 1L;
        }
        if (size == null){
            size = 10L;
        }
        page.setCurrent(current);
        page.setSize(size);
        IPage list = fileModelService.page(page,wrapper);
        return  new Response<IPage>().success().setData(list);
    }
    @GetMapping("/download")
    public void download(@RequestParam("fileMd5")String fileMd5, @RequestParam("fileName")String fileName, HttpServletResponse response){
        fileModelService.downloadFile(fileMd5,fileName,response);
    }

}
