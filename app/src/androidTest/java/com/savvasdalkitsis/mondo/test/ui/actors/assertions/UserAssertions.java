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
package com.savvasdalkitsis.mondo.test.ui.actors.assertions;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.test.data.TestCurrency;

import static com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions.seesText;
import static com.savvasdalkitsis.mondo.test.ui.assertions.SeesAssertions.seesTextInsideViewWithId;

public class UserAssertions {

    public void seesBalance(double balance, TestCurrency currency) {
        seesText("Balance");
        seesText(balance);
        seesTextInsideViewWithId(R.id.view_balance, currency.displayString());
    }

    public void seesErrorRetrievingBalance() {
        seesText("N/A");
    }

    public void seesSpentToday(double amount, TestCurrency currency) {
        seesText("Spent today");
        seesText(amount);
        seesTextInsideViewWithId(R.id.view_spent_today, currency.displayString());
    }

    public void seesTransaction(double amount, String merchant) {
        seesText(merchant);
        seesText(amount);
    }

    public void seesTransaction(int amount, String merchant, int localAmount, String localCurrencySymbol) {
        seesTransaction(amount, merchant);
        seesText(localAmount);
        seesText(localCurrencySymbol);
    }

    public void seesErrorRetrievingTransactions() {
        seesText("Error retrieving transactions");
    }

    public void seesAuthenticationScreen() {
        seesText("Welcome to Mondo");
    }
}
