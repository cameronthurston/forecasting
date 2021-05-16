package com.DERP.forecasting.services;

import com.DERP.forecasting.dao.CustomerRepository;
import com.DERP.forecasting.domain.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired CustomerRepository repository;

    public Mono<Customer> save(Customer customer) {

        return repository.save(customer).map(it -> {

            if (it.getFirstName().equals("NULL")) {
                throw new IllegalStateException();
            } else {
                return it;
            }
        });
    }

    public Mono<Customer> findById(Long id) {

        return repository.findById(id).map(it -> {
                return it;
        });
    }
}
