package cn.feilong.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = { "com.dili.common.mapper" })
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
