package com.comrade.clients;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastClientConfig {
    public void init(){
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<String, String> stringStringIMap = hazelcastInstance.getMap("COMRADE");
        System.out.println(String.format("SIZE OF CACHED MAP %d ",stringStringIMap.size()));
    }

    public static void main(String[] args) {
        HazelcastClientConfig hazelcastClientConfig = new HazelcastClientConfig();
        hazelcastClientConfig.init();
    }
}
