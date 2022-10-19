package com.example.icremytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class IcremyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcremyTestApplication.class, args);
        log.info("\n **************** APP SERVICE STARTED ****************");
    }

}
