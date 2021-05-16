package com.DERP.forecasting.dao;

import com.DERP.forecasting.domain.models.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

    Flux<Customer> findByLastName(String lastName);

    Flux<Customer> findByCityName(String cityName);

    Mono<Customer> findById(long id);
}
