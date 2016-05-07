package com.savvasdalkitsis.mondo.model.currency;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class RecognizedCurrencySymbolsTest {

    private static final String UNKNOWN_CURRENCY = "UNKNOWN_CURRENCY";
    private final RecognizedCurrencySymbols currencySymbols = new RecognizedCurrencySymbols();

    @Test
    public void mapsAllKnownCurrenciesToTheirSymbols() {
        for (RecognizedCurrencies currency : RecognizedCurrencies.values()) {
            assertThat(currencySymbols.getSymbolFor(currency.getCurrencyName()), equalTo(currency.getCurrencySymbol()));
        }
    }

    @Test
    public void mapsUnknownCurrencyToTheirName() {
        assertThat(currencySymbols.getSymbolFor(UNKNOWN_CURRENCY), equalTo(UNKNOWN_CURRENCY));
    }

}