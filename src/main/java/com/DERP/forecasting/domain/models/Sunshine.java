package com.DERP.forecasting.domain.models;

import lombok.Data;

@Data
public class Sunshine {
    public int type;
    public int id;
    public float message;
    public String country;
    public float sunrise;
    public float sunset;
}
