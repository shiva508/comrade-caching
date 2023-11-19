package com.comrade.service;

import com.comrade.model.CommandResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CommandService {

    private static List<CommandResponse> commandResponses = new ArrayList<>();

    @Cacheable("rextax")
    public CommandResponse getResponseByValue(String value){
        return commandResponses.stream().filter(commandResponse -> commandResponse.getValue().equals(value)).findFirst().orElseThrow(()->new RuntimeException("Issue occurred"));
    }

    @PostConstruct
    public void init(){
        commandResponses = IntStream.range(1,20).mapToObj(data->CommandResponse.builder().value(String.valueOf(data)).build()).toList();
    }
}
