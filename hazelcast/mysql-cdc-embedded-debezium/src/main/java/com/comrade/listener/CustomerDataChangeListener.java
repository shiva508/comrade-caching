package com.comrade.listener;

import com.comrade.service.CustomerService;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.*;
import static io.debezium.data.Envelope.Operation;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class CustomerDataChangeListener {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final CustomerService customerService;
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    public CustomerDataChangeListener(Configuration customerConnectorConfiguration, CustomerService customerService) {

        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(customerConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();

        this.customerService = customerService;
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();

        log.info("Key = '" + sourceRecord.key() + "' value = '" + sourceRecord.value() + "'");

        Struct sourceRecordChangeValue= (Struct) sourceRecord.value();

        if (sourceRecordChangeValue != null) {
            Operation operation = Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));
            System.out.println(operation);
            if(operation != Operation.READ) {
                String record = operation == Operation.DELETE ? BEFORE : AFTER;

                Struct struct = (Struct) sourceRecordChangeValue.get(record);
                Map<String, Object> payload = struct.schema().fields().stream()
                        .map(Field::name)
                        .filter(fieldName -> struct.get(fieldName) != null)
                        .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                        .collect(toMap(Pair::getKey, Pair::getValue));

                this.customerService.replicateData(payload, operation);
                log.info("Updated Data: {} with Operation: {}", payload, operation.name());
            }
        }
    }

    @PostConstruct
    private void start() {
        log.info("=============>start<======================");
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            log.info("=============>stop<======================");
            this.debeziumEngine.close();
        }
    }
}
