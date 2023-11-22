package com.comrade;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.debezium.dsl.Debezium;
import org.springframework.integration.debezium.inbound.DebeziumMessageProducer;
import org.springframework.integration.debezium.support.DebeziumHeaders;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class SpringIntegrationDebeziumSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationDebeziumSupportApplication.class, args);
    }

//    @Bean
//    public MessageChannel debeziumInputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageProducer debeziumMessageProducer(
//            DebeziumEngine.Builder<ChangeEvent<byte[], byte[]>> debeziumEngineBuilder, MessageChannel debeziumInputChannel) {
//
//        DebeziumMessageProducer debeziumMessageProducer = new DebeziumMessageProducer(debeziumEngineBuilder);
//        debeziumMessageProducer.setOutputChannel(debeziumInputChannel);
//        return debeziumMessageProducer;
//    }
//
//    @ServiceActivator(inputChannel = "debeziumInputChannel")
//    public void handler(Message<?> message) {
//
//        Object destination = message.getHeaders().get(DebeziumHeaders.DESTINATION);
//
//        String key = new String((byte[]) message.getHeaders().get(DebeziumHeaders.KEY));
//
//        String payload = new String((byte[]) message.getPayload());
//
//        System.out.println("KEY: " + key + ", DESTINATION: " + destination + ", PAYLOAD: " + payload);
//    }

    @Bean
    public IntegrationFlow debeziumInbound(DebeziumEngine.Builder<ChangeEvent<byte[], byte[]>> debeziumEngineBuilder) {
        return IntegrationFlow
                .from(Debezium
                        .inboundChannelAdapter(debeziumEngineBuilder)
                        .headerNames("special*")
                        .contentType("application/json")
                        .enableBatch(false))
                .handle(m -> System.out.println(new String((byte[]) m.getPayload())))
                .get();
    }

}