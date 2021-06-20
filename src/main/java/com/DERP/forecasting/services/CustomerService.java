package com.DERP.forecasting.services;

import com.DERP.forecasting.dao.CustomerRepository;
import com.DERP.forecasting.domain.dto.EnrollmentRequest;
import com.DERP.forecasting.domain.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    private Mono<Customer> save(Customer customer) {

        return repository.save(customer).map(it -> {

            if (it.getFirstName().equals("NULL")) {
                throw new IllegalStateException();
            } else {
                return it;
            }
        });
    }

    public Mono<Customer> findById(UUID id) {

        return repository.findById(id).map(it -> it);
    }

    public Mono<UUID> createCustomer(EnrollmentRequest enrollmentRequest) {
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .firstName(enrollmentRequest.getFirstName())
                .lastName(enrollmentRequest.getLastName())
                .cityName(enrollmentRequest.getCity())
                .stateCode(enrollmentRequest.getState())
                .countryCode(enrollmentRequest.getCountryCode())
                .enrollmentDate(LocalDateTime.now())
                .build();
        return this.save(customer).map(Customer::getId);
    }
}
