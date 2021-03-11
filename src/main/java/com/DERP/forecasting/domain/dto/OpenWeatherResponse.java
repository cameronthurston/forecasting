package com.DERP.forecasting.domain.dto;

import com.DERP.forecasting.domain.models.*;
import lombok.Data;

@Data
public class OpenWeatherResponse {
    public Coordinate coord;
    public Weather weather;
    public String base;
    public Air main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public Double dt;
    public Sunshine sys;
    public int timezone;
    public Double id;
    public String name;
    public String cod;
}
