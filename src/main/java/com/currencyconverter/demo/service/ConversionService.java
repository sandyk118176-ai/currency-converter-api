package com.currencyconverter.demo.service;

import com.currencyconverter.demo.dto.ConversionRequest;
import com.currencyconverter.demo.dto.ConversionResponse;
import com.currencyconverter.demo.entity.ConversionHistory;
import com.currencyconverter.demo.repository.ConversionHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class ConversionService {

    private final ExchangeRateService exchangeRateService;
    private final ConversionHistoryRepository historyRepository;

    public ConversionService(ExchangeRateService exchangeRateService, ConversionHistoryRepository historyRepository) {
        this.exchangeRateService = exchangeRateService;
        this.historyRepository = historyRepository;
    }

    public ConversionResponse convert(ConversionRequest request) {
        BigDecimal rate = exchangeRateService.getExchangeRate(
                request.getFromCurrency(), request.getToCurrency());

        BigDecimal convertedAmount = request.getAmount()
                 .multiply(rate)
                 .setScale(2, RoundingMode.HALF_UP);

        ConversionHistory history = new ConversionHistory(
                 request.getFromCurrency(),
                 request.getToCurrency(),
                 request.getAmount(),
                 convertedAmount,
                 rate,
                 LocalDateTime.now()
        );
        historyRepository.save(history);

        return new ConversionResponse(
               request.getFromCurrency(),
               request.getToCurrency(),
               request.getAmount(),
               convertedAmount,
               rate
        );
    }
    public List<ConversionHistory> getHistory() {
        return historyRepository.findAll();
    }
}
