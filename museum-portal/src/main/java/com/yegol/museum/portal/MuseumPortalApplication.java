package com.yegol.museum.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.yegol.museum.portal.mapper")
public class MuseumPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuseumPortalApplication.class, args);
    }

}
