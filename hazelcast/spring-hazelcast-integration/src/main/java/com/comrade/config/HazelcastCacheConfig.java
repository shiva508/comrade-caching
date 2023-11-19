package com.comrade.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastCacheConfig {
    @Bean
    public HazelcastInstance hazelcastInstance(){
        /*Config config = new Config();
        config.setClusterName("dev-shiva");
        return Hazelcast.newHazelcastInstance(config);*/
        return Hazelcast.newHazelcastInstance();
    }
}
