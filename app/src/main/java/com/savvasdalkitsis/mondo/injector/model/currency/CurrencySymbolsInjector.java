package com.savvasdalkitsis.mondo.injector.model.currency;

import com.savvasdalkitsis.mondo.model.currency.CurrencySymbols;
import com.savvasdalkitsis.mondo.model.currency.RecognizedCurrencySymbols;

public class CurrencySymbolsInjector {
    public static CurrencySymbols currencySymbols() {
        return new RecognizedCurrencySymbols();
    }
}
