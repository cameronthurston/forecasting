package com.DERP.forecasting.domain.dto;

import lombok.Data;

@Data
public class WeatherLocation {
    public String cityName;
    public String stateCode;
    public String countryCode;
}
