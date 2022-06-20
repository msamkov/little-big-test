package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.RatesDto;

import java.util.List;

public interface ExchangeRateProviderService {

    List<RatesDto> rates();

}
