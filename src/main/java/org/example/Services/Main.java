package org.example.Services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching  // Enable Caching
@ComponentScan(basePackages = {"org.example"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}



