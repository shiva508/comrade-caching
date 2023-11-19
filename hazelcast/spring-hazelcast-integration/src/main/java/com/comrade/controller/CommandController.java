package com.comrade.controller;

import com.comrade.model.CommandResponse;
import com.comrade.service.CommandService;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
public class CommandController {


    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private CommandService commandService;

    private ConcurrentMap<String,String> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    @PostMapping("/put")
    public CommandResponse put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        retrieveMap().put(key, value);
        return new CommandResponse(value);
    }

    @GetMapping("/get")
    public CommandResponse get(@RequestParam(value = "key") String key) {
        String value = retrieveMap().get(key);
        return new CommandResponse(value);
    }

    @GetMapping("/getval/{val}")
    public CommandResponse commandResponse(@PathVariable("val") String val){
        return commandService.getResponseByValue(val);
    }
}
