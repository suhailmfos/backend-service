package com.jarvis.backend;

import com.jarvis.backend.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("Application Started...");
        SpringApplication.run(Application.class, args);
    }
}