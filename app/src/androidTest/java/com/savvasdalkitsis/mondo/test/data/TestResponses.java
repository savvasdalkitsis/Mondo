package com.savvasdalkitsis.mondo.test.data;

public class TestResponses {

    public static String balance(double balance, TestCurrency currency) {
        return "{\"balance\": " + balance + ", \"currency\": \"" + currency.apiName() + "\"}";
    }

    public static String spentToday(double amount, TestCurrency currency) {
        return "{\"spent_today\": " + amount + ", \"currency\": \"" + currency.apiName() + "\"}";
    }
}
