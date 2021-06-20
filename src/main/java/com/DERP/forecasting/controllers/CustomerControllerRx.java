package com.DERP.forecasting.controllers;

import com.DERP.forecasting.domain.dto.EnrollmentRequest;
import com.DERP.forecasting.domain.models.Customer;
import com.DERP.forecasting.services.CustomerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerControllerRx {

    @Autowired
    CustomerService customerService;

    private Gson gson = new Gson();

    @GetMapping("/customerInfo")
    Mono<String> getCustomerInfo(UUID id){
        return customerService.findById(id).map(y -> gson.getAdapter(Customer.class).toJson(y));
    }

    @PostMapping("/enrollment")
    ResponseEntity<String> createCustomer(@RequestBody EnrollmentRequest enrollmentRequest){
        String customerId = customerService.createCustomer(enrollmentRequest).map(UUID::toString).block();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.VARY, HttpHeaders.ACCEPT, HttpHeaders.ACCEPT_LANGUAGE)
                .body(customerId);
    }
}
