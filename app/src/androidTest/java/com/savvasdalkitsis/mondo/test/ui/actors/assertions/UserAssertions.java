package com.savvasdalkitsis.mondo.test.ui.actors.assertions;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.test.data.TestCurrency;

import static com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions.seesText;
import static com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions.seesTextOnViewWithId;

public class UserAssertions {

    public void seesBalance(double balance, TestCurrency currency) {
        seesText("Balance");
        seesText(balance);
        seesTextOnViewWithId(R.id.view_balance_currency, currency.displayString());
    }

    public void seesErrorRetrievingBalance() {
        seesText("Error retrieving balance");
    }

    public void seesSpentToday(double amount, TestCurrency currency) {
        seesText("Spent today");
        seesText(amount);
        seesTextOnViewWithId(R.id.view_spent_today_currency, currency.displayString());
    }

    public void seesTransaction(double amount, String merchant) {
        seesText(merchant);
        seesText(amount);
    }

    public void seesErrorRetrievingTransactions() {
        seesText("Error retrieving transactions");
    }

    public void seesAuthenticationScreen() {
        seesText("Authenticate");
    }
}
