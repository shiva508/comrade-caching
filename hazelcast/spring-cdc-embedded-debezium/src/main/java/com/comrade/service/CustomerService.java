package com.comrade.service;

import com.comrade.entity.CustomerEntity;
import com.comrade.model.CustomerModel;
import com.comrade.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void replicateData(Map<String, Object> customerData, Operation operation) {
        log.info("Data with {} operation {}",customerData,operation);
        final ObjectMapper mapper = new ObjectMapper();
        final CustomerModel customer = mapper.convertValue(customerData, CustomerModel.class);

        log.info("parssed data {}",customer);
        if (Operation.DELETE == operation) {
            //customerRepository.deleteById(customer.getId());
        } else {
            //customerRepository.save(customer);
        }
    }
}
