package com.comrade.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;

@Service
public class CustomCacheService {

    public void service(){
        Hazelcast.getAllHazelcastInstances().forEach(hazelcastInstance -> {
            System.out.println(hazelcastInstance);
        });
    }
}
