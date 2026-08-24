package com.currencyconverter.demo.service;

import com.currencyconverter.demo.dto.ConversionRequest;
import com.currencyconverter.demo.dto.ConversionResponse;
import com.currencyconverter.demo.repository.ConversionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ConversionServiceTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private ConversionHistoryRepository historyRepository;

    @InjectMocks
    private ConversionService conversionService;

    private ConversionRequest request;

    @BeforeEach
    void setUp() {
        request = new ConversionRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("INR");
        request.setAmount(new BigDecimal("100"));
    }

    @Test
    void convert_calculatesCorrectAmount() {
        when(exchangeRateService.getExchangeRate("USD", "INR"))
                  .thenReturn(new BigDecimal("95.70"));

        ConversionResponse response = conversionService.convert(request);

        assertEquals(new BigDecimal("9570.00"), response.getConvertedAmount());
        assertEquals("USD", response.getFromCurrency());
        assertEquals("INR", response.getToCurrency());
    }

    @Test
    void convert_savesHistoryRecord() {
        when(exchangeRateService.getExchangeRate("USD", "INR"))
                 .thenReturn(new BigDecimal("95.70"));

        conversionService.convert(request);
        org.mockito.Mockito.verify(historyRepository).save(any());
    }
}
