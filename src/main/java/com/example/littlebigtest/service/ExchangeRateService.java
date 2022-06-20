package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.RatesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final List<ExchangeRateProviderService> exchangeRateProviderServices;

    public List<RatesDto> rates() {
        List<RatesDto> rates = new ArrayList<>();
        exchangeRateProviderServices.forEach(
                exchangeRateProviderService -> rates.addAll(exchangeRateProviderService.rates()));
        return rates;
    }
}
