package com.comrade.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "customer.datasource")
public class CustomerConfig {

    private String host;

    private String database;

    private String port;

    private String username;

    private String password;
}
