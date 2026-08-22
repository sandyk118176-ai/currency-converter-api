package com.currencyconverter.demo.dto;

import java.math.BigDecimal;

public class ConversionResponse {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal originalAmount;
    private BigDecimal convertedAmount;
    private BigDecimal exchangeRate;

    public ConversionResponse() {
    }

    public ConversionResponse(String fromCurrency, String toCurrency, BigDecimal originalAmount, 
                              BigDecimal convertedAmount, BigDecimal exchangeRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;                        
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    } 

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    
}
