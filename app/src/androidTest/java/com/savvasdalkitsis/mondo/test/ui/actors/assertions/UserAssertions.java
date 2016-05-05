package com.savvasdalkitsis.mondo.test.ui.actors.assertions;

import com.savvasdalkitsis.mondo.test.data.TestCurrency;

import static com.savvasdalkitsis.mondo.test.assertions.SeesAssertions.seesText;

public class UserAssertions {

    public void seesBalance(int balance, TestCurrency currency) {
        seesText("Balance");
        seesText(String.valueOf(balance));
        seesText(currency.displayString());
    }
}
