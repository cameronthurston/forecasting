package com.DERP.forecasting.domain.error;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;

public class WeatherProviderWebClientException extends WebClientException {
    private String reason;
    private HttpStatus statusCode;
    private JSONObject error;

    public WeatherProviderWebClientException(String reason, HttpStatus statusCode, JSONObject error) {

        super(error.toJSONString());
        this.reason = reason;
        this.statusCode = statusCode;
        this.error = error;
    }

    public HttpStatus getStatusCode() {

        return this.statusCode;
    }

    public JSONObject getError() {

        return this.error;
    }
}
