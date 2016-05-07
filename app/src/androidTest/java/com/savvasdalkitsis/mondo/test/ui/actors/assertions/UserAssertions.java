package com.savvasdalkitsis.mondo.test.ui.actors.assertions;

import com.savvasdalkitsis.mondo.test.data.TestCurrency;

import static com.savvasdalkitsis.mondo.test.assertions.SeesAssertions.seesText;

public class UserAssertions {

    public void seesBalance(double balance, TestCurrency currency) {
        seesText("Balance");
        seesText(String.valueOf(balance));
        seesText(currency.displayString());
    }

    public void seesErrorContactingMondo() {
        seesText("Error talking to Mondo");
    }
}
