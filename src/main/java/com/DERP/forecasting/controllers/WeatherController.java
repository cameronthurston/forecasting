package com.DERP.forecasting.controllers;

import com.DERP.forecasting.domain.dto.OpenWeatherRequest;
import com.DERP.forecasting.domain.dto.OpenWeatherResponse;
import com.DERP.forecasting.domain.dto.WeatherLocation;
import com.DERP.forecasting.services.WeatherService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/current")
    Mono<OpenWeatherResponse> getCurrentWeather(@RequestBody WeatherLocation weatherLocation){
        return this.weatherService.search5DayWeatherForecase(weatherLocation);
    }
}
