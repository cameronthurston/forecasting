package com.DERP.forecasting.domain.dto;

import com.DERP.forecasting.domain.enums.Units;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherRequest {
    public List<String> q;
    public String lat;
    public String lng;
    public String zip;
    public String appid;
    public String mode;
    public String cnt;
    public Units units;
    public String lang;
}
