package top.liuyanhua.skydrive.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.liuyanhua.skydrive.models.File;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;
    @Test
    public void margeFile() throws IOException, NoSuchAlgorithmException {
        File file = new File();
        file.setFileType(".zip");
        file.setFileName("test.zip");
        file.setMd5("94fcbbcefaf786b9273340704a00eab2");
        fileService.margeFile(file);
    }
}