package com.kit.mike;

import com.kit.mike.dao.DeviceDao;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class KitIoTCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitIoTCarApplication.class, args);

    }

}
