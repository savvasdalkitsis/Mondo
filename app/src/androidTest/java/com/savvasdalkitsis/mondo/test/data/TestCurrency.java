package com.savvasdalkitsis.mondo.test.data;

public enum TestCurrency {
    GBP("GBP", "£");

    private final String apiName;
    private final String displayString;

    TestCurrency(String apiName, String displayString) {
        this.apiName = apiName;
        this.displayString = displayString;
    }

    public String apiName() {
        return apiName;
    }

    public String displayString() {
        return displayString;
    }
}
