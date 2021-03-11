package com.DERP.forecasting.domain.models;

import lombok.Data;

@Data
public class Air {
    public int temp;
    public int feels_like;
    public int temp_min;
    public int temp_max;
    public int pressure;
    public int humidity;
}
