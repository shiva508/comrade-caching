package com.comrade.cache;

import com.comrade.model.CommandResponse;
import com.hazelcast.map.MapStore;
import com.hazelcast.map.PostProcessingMapStore;
import com.hazelcast.spring.context.SpringAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@SpringAware
@Component
@Slf4j
public class ProcessingStore implements MapStore<String, CommandResponse> {

    public static Map<String,CommandResponse> commandResponses = new HashMap<>();

    @Override
    public void store(String integer, CommandResponse commandResponse) {
        log.info("store {}",integer);
        commandResponses.computeIfAbsent(integer, cr -> commandResponse);
    }

    @Override
    public void storeAll(Map<String, CommandResponse> map) {
        System.out.println(map);
        log.info("storeAll {}",map);
        commandResponses.putAll(map);
    }

    @Override
    public void delete(String integer) {
        System.out.println(integer);
        log.info("store {}",integer);
        commandResponses.remove(integer);
    }

    @Override
    public void deleteAll(Collection<String> collection) {
        log.info("deleteAll {}",collection);
        System.out.println(collection);

    }

    @Override
    public CommandResponse load(String integer) {
        log.info("load {}",integer);
        return commandResponses.get(integer);
    }

    @Override
    public Map<String, CommandResponse> loadAll(Collection<String> collection) {
        log.info("loadAll {}",collection);
        return commandResponses;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        log.info("loadAllKeys");
        return new ArrayList<>(commandResponses.keySet());
    }
}
