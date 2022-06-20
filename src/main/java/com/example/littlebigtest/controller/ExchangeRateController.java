package com.example.littlebigtest.controller;

import com.example.littlebigtest.dto.RatesDto;
import com.example.littlebigtest.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExchangeRateController {


    private final ExchangeRateService exchangeRateService;

    @RequestMapping(value = {  "/exchange-rate", "/exchange-rates.html", "dollar" }, method = RequestMethod.GET)
    public String index(Model model) {
        List<RatesDto> rates = exchangeRateService.rates();
        model.addAttribute("rates", rates);
        return "exchangeRate";
    }

}
