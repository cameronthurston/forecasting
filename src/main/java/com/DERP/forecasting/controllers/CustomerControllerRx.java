package com.DERP.forecasting.controllers;

import com.DERP.forecasting.domain.models.Customer;
import com.DERP.forecasting.services.CustomerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerControllerRx {

    @Autowired
    CustomerService customerService;

    private Gson gson = new Gson();

    @GetMapping("/customerInfo")
    Mono<String> getCustomerInfo(Long id){
        return customerService.findById(id).map(y -> gson.getAdapter(Customer.class).toJson(y));
    }
}
