package com.currencyconverter.demo.dto;

import java.math.BigDecimal;

public class ConversionRequest {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
    
    public ConversionRequest() {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
