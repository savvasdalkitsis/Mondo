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
