package com.comrade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumMySqlConnectorConfig {

    @Autowired
    private CustomerMySqlConfig customerMySqlConfig;

    @Bean(name = "customerConnector")
    public io.debezium.config.Configuration customerConnector() throws IOException {

        File offsetStorageTempFile = File.createTempFile("offsetssql_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistorysql_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "mysql-engine")
                .with("topic.prefix", "mysql-connector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", customerMySqlConfig.getHost())
                .with("database.port", customerMySqlConfig.getPort())
                .with("database.user", customerMySqlConfig.getUsername())
                .with("database.password", customerMySqlConfig.getPassword())
                .with("database.dbname", customerMySqlConfig.getDatabase())
                .with("database.server.id", "85745")
                .with("include.schema.changes", "false")
                .with("errors.log.include.messages", "true")
                .with("table.include.list","customerdb.customer")
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", dbHistoryTempFile.getAbsolutePath())
                .build();
        //WORKING
        //NEED TO TRY WITH BELLOW
        /*.with("name", "customer-postgres-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("topic.prefix", "my-app-connector")
                .with("offset.flush.interval.ms", "6000")
                .with("database.hostname", customerConfig.getHost())
                .with("database.port", customerConfig.getPort())
                .with("database.user", customerConfig.getUsername())
                .with("database.password", customerConfig.getPassword())
                .with("database.dbname", customerConfig.getDatabase())
                .with("database.include.list", customerConfig.getDatabase())
                .with("table.include.list","inventory.customers")
                .with("include.schema.changes", "false")
                .with("database.allowPublicKeyRetrieval", "true")
                //.with("database.server.id", "10181")
                .with("database.server.name", "customer-postgres-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", dbHistoryTempFile.getAbsolutePath())
                .with("snapshot.delay.ms", "100")
                .with("errors.log.include.messages", "true")*/
    }
}
