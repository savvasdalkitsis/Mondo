package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.model.currency.CurrencySymbols;

import java.util.HashMap;

public class FakeCurrencySymbols implements CurrencySymbols {

    private final HashMap<String, String> mappings = new HashMap<>();

    public void mapping(String currency, String symbol) {
        mappings.put(currency, symbol);
    }

    @Override
    public String getSymbolFor(String currency) {
        return mappings.get(currency);
    }
}
