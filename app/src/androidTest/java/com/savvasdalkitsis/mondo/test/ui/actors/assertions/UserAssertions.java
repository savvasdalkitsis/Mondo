package com.savvasdalkitsis.mondo.test.ui.actors.assertions;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.test.data.TestCurrency;
import com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions;

import static com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions.seesText;

public class UserAssertions {

    public void seesBalance(double balance, TestCurrency currency) {
        seesText("Balance");
        seesText(balance);
        SeesAssertions.seesTextInsideViewWithId(R.id.view_balance, currency.displayString());
    }

    public void seesErrorRetrievingBalance() {
        seesText("N/A");
    }

    public void seesSpentToday(double amount, TestCurrency currency) {
        seesText("Spent today");
        seesText(amount);
        SeesAssertions.seesTextInsideViewWithId(R.id.view_spent_today, currency.displayString());
    }

    public void seesTransaction(double amount, String merchant) {
        seesText(merchant);
        seesText(amount);
    }

    public void seesErrorRetrievingTransactions() {
        seesText("Error retrieving transactions");
    }

    public void seesAuthenticationScreen() {
        seesText("Welcome to Mondo");
    }
}
