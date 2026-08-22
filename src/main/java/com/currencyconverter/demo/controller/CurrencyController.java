package com.currencyconverter.demo.controller;

import com.currencyconverter.demo.dto.ConversionRequest;
import com.currencyconverter.demo.dto.ConversionResponse;
import com.currencyconverter.demo.service.ConversionService;
import com.currencyconverter.demo.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

import com.currencyconverter.demo.entity.ConversionHistory;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final ExchangeRateService exchangeRateService;
    private final ConversionService conversionService;

    public CurrencyController(ConversionService conversionService, ExchangeRateService exchangeRateService) {
        this.conversionService = conversionService;
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping("/convert")
    public ConversionResponse convert(@RequestBody ConversionRequest request) {
        return conversionService.convert(request);
    }

    @GetMapping("/currencies")
    public Map<String, String> getCurrencies() {
        return exchangeRateService.getSupportedCurrencies();
    }

    @GetMapping("/history")
    public List<ConversionHistory> getHistory() {
        return conversionService.getHistory();
    }
    
}
