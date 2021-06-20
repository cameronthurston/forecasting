package com.DERP.forecasting.domain.dto;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class EnrollmentRequest {
    String firstName;
    String middleName;
    String lastName;
    String city;
    String state;
    String countryCode;
    @Nullable
    Float lat;
    @Nullable
    Float lng;
}
