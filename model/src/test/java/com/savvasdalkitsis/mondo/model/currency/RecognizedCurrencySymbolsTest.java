/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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