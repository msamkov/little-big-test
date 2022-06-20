package com.example.littlebigtest.service;

import com.example.littlebigtest.dto.RatesDto;
import com.example.littlebigtest.dto.ValCurs;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CbrExchangeRateProviderService implements ExchangeRateProviderService {

    private final XmlMapper mapper;
    private final RestTemplate restTemplate;
    private final String CBR;

    public CbrExchangeRateProviderService(@Value("${cbr.exchange-rates}") final String urlCBR,
                                          XmlMapper mapper,
                                          RestTemplate restTemplate) {
        this.CBR = urlCBR;
        this.mapper = mapper;
        this.restTemplate = restTemplate;

    }

    @SneakyThrows
    private ValCurs getValCurs() {
        ResponseEntity<String> response = restTemplate.getForEntity(CBR, String.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error loading the exchange rate");
        }
        return mapper.readValue(response.getBody(),  ValCurs.class);
    }

    @Override
    public List<RatesDto> rates() {
        return getValCurs()
                .getValutes()
                .stream()
                .filter(valute -> valute.getCharCode().equals("USD") || valute.getCharCode().equals("EUR"))
                .map(valute -> new RatesDto()
                        .setProvider("ЦБР")
                        .setName(valute.getCharCode())
                        .setBuy(Float.parseFloat(valute.getValue().replace(',', '.')))
                        .setSell(Float.parseFloat(valute.getValue().replace(',', '.')))
                )
                .collect(Collectors.toList());
    }
}
