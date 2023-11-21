### mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8080"
### mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
### docker run -it --rm --name postgres -p 5432:5432 -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres debezium/example-postgres:1.2
### docker exec -it postgres psql -U postgres
### SET search_path TO inventory;
### \dt;
### SELECT * FROM customers;
