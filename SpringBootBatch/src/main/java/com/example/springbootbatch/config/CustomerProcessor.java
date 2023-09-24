package com.example.springbootbatch.config;

import com.example.springbootbatch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        return customer.getCountry().equals("Germany") ? customer : null;
    }
}
