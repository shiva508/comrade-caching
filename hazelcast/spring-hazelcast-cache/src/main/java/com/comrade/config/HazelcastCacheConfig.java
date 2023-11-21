package com.comrade.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastCacheConfig {
    //@Bean
    public HazelcastInstance hazelcastInstance(){
        return Hazelcast.newHazelcastInstance();
    }
}
