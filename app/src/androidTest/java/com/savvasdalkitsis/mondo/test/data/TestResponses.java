package com.savvasdalkitsis.mondo.test.data;

public class TestResponses {

    public static String balance(double balance, TestCurrency currency) {
        return "{\"balance\": " + balance + ", \"currency\": \"" + currency.displayString() + "\"}";
    }
}
