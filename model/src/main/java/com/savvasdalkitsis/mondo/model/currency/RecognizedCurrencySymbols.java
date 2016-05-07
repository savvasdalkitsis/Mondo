package com.savvasdalkitsis.mondo.model.currency;

public class RecognizedCurrencySymbols implements CurrencySymbols {

    @Override
    public String getSymbolFor(String currencyName) {
        RecognizedCurrencies currency = RecognizedCurrencies.fromName(currencyName);
        return currency != null ? currency.getCurrencySymbol() : currencyName;
    }
}
