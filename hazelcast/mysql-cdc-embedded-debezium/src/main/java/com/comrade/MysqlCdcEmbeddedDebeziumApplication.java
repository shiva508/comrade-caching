package com.comrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlCdcEmbeddedDebeziumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlCdcEmbeddedDebeziumApplication.class, args);
    }

}