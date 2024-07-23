package com.dwikyryan.microservices.currency_exchange_service;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Environment environment;
    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository){
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        var currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null){
            throw new RuntimeException("Unable to find data");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
