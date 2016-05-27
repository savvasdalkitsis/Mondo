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

public enum RecognizedCurrencies {

    GBP("GBP", "£"),
    EUR("EUR", "€"),
    USD("USD", "$");

    private String currencyName;
    private String currencySymbol;

    RecognizedCurrencies(String currencyName, String currencySymbol) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public static RecognizedCurrencies fromName(String currencyName) {
        for (RecognizedCurrencies currency : values()) {
            if (currency.currencyName.equals(currencyName)) {
                return currency;
            }
        }
        return null;
    }
}
