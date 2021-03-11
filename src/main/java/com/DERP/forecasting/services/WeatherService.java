package com.DERP.forecasting.services;

import com.DERP.forecasting.configuration.OpenWeatherProperties;
import com.DERP.forecasting.domain.dto.OpenWeatherResponse;
import com.DERP.forecasting.domain.dto.WeatherLocation;
import com.DERP.forecasting.domain.error.WeatherProviderWebClientException;
import net.minidev.json.JSONObject;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;

@Service
public class WeatherService {
    private final OpenWeatherProperties weatherProperties;

    private final WebClient webClient =  WebClient.builder()
            .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                if (clientResponse.statusCode().isError()) {
                    return clientResponse.bodyToMono(JSONObject.class)
                            .flatMap(errorDetails ->
                                    Mono.error(new WeatherProviderWebClientException("Error Status", clientResponse.statusCode(), errorDetails))) ;
                }
                return Mono.just(clientResponse);
            }))
            .build();

    public WeatherService(OpenWeatherProperties weatherProperties) {
        this.weatherProperties = weatherProperties;
    }

    public Mono<OpenWeatherResponse> search5DayWeatherForecase(WeatherLocation location) {
        if(location.getCityName() == null){
            return Mono.error(MissingRequiredPropertiesException::new);
        }

        ArrayList<String> q = new ArrayList<>();
        q.add(location.cityName);
        if(location.getStateCode() != null) { q.add(location.stateCode); }
        if(location.getCountryCode() != null) { q.add(location.countryCode); }

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(weatherProperties.openWeatherMapUri+"/forecast")
                .queryParam("q", q)
                .queryParam("appId", weatherProperties.appId);

        URI uri = urlBuilder.build().toUri();
        Mono<OpenWeatherResponse> response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class);
        return response;
    }
}
