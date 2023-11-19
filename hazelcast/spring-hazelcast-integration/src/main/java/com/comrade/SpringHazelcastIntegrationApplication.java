package com.comrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringHazelcastIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHazelcastIntegrationApplication.class, args);
    }
}