package com.DERP.forecasting.unit;

import com.DERP.forecasting.dao.CustomerRepository;
import com.DERP.forecasting.domain.dto.EnrollmentRequest;
import com.DERP.forecasting.domain.models.Customer;
import com.DERP.forecasting.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.UUID;


@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void givenCustomerService_WhenCustomerSaved_ThenUUIDReturned(){
        EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
        enrollmentRequest.setFirstName("Cameron");
        UUID id = UUID.randomUUID();
        Mockito.when(repository.save(Mockito.any(Customer.class))).thenReturn(
                Mono.just(Customer.builder().firstName("Cameron").id(id).build()));


        Assertions.assertNotNull(this.customerService.createCustomer(enrollmentRequest).block());
        Assertions.assertEquals(id, this.customerService.createCustomer(enrollmentRequest).block());
    }
}
