package top.liuyanhua.skydrive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("top.liuyanhua.skydrive.mapper")
@SpringBootApplication
@EnableSwagger2
public class SkydriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkydriveApplication.class, args);
    }

}
