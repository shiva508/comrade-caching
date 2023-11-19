package com.comrade.members;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastMemberConfig {
    public void init(){
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        Map<String, String> stringStringMap = hazelcastInstance.getMap("COMRADE");
        stringStringMap.put("Shiva","Dasari");
        stringStringMap.put("Satish","Dasari");
        stringStringMap.put("Ravi","Dasari");

        System.out.println(String.format("Size of map %d",stringStringMap.size()));
        System.out.println(String.format("Getting 'Shiva' key = %s", stringStringMap.get("Shiva")));
        System.out.println(String.format("Size of map %d",stringStringMap.size()));

        Queue<String> stringQueue = hazelcastInstance.getQueue("COMRADE");
        stringQueue.offer("Bruce Wyane");
        stringQueue.offer("Steev Rogers");
        stringQueue.offer("Bruce Banner");
        System.out.println(String.format("Bruce Wyane %s" , stringQueue.poll()));
        System.out.println(String.format("Stev Rogers %s ", stringQueue.peek()));
        System.out.println(String.format("Size of map %d",stringQueue.size()));
    }

    public static void main(String[] args) {
        HazelcastMemberConfig hazelcastMemberConfig = new HazelcastMemberConfig();
        hazelcastMemberConfig.init();
    }
}
