package com.currencyconverter.demo.service;

import com.currencyconverter.demo.dto.FrankfurterResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;
import java.util.Map;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {
    private final WebClient webClient;

    
    public ExchangeRateService(WebClient frankfurterWebClient) {
        this.webClient = frankfurterWebClient;
    }

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) {
        FrankfurterResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base", fromCurrency)
                        .queryParam("symbols", toCurrency)
                        .build())
                .retrieve()
                .bodyToMono(FrankfurterResponse.class)
                .block();
        if(response == null || response.getRates() == null || !response.getRates().containsKey(toCurrency)) {
            throw new IllegalStateException("Unable to fetch exchange rate for " + fromCurrency + " to " + toCurrency);
        }

        return response.getRates().get(toCurrency);
    }

    public Map<String, String> getSupportedCurrencies() {
        return webClient.get()
                .uri("/currencies")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .block();
    }
}
