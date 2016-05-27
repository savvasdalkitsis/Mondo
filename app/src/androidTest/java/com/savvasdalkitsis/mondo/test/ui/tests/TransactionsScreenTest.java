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
package com.savvasdalkitsis.mondo.test.ui.tests;

import com.savvasdalkitsis.mondo.test.R;

import org.junit.Test;

import static com.savvasdalkitsis.mondo.test.data.TestCurrency.GBP;
import static com.shazam.gwen.Gwen.given;
import static com.shazam.gwen.Gwen.then;
import static com.shazam.gwen.Gwen.when;

public class TransactionsScreenTest extends MondoTest {

    @Test
    public void displaysUserBalance() {
        given(user).hasBalance(12340, GBP);

        when(user).launchesMondo();

        then(user).seesBalance(123.4, GBP);
    }

    @Test
    public void displaysErrorWhenGettingBalanceFails() {
        given(mondo).isDown();

        when(user).launchesMondo();

        then(user).seesErrorRetrievingBalance();
    }

    @Test
    public void displaysSpentToday() {
        given(user).hasSpentToday(6660, GBP);

        when(user).launchesMondo();

        then(user).seesSpentToday(66.6, GBP);
    }

    @Test
    public void displaysUserTransactions() {
        given(user).hasTransactions(R.raw.transactions_100starbucks_33mcdonalds);

        when(user).launchesMondo();

        then(user).seesTransaction(100, "Starbucks");
        then(user).seesTransaction(33, "McDonald's");
    }

    @Test
    public void displaysForeignTransactionsDisplayingLocalCurrency() {
        given(user).hasTransactions(R.raw.transactions_starbucks_100_gbp_dollars_143_usd);

        when(user).launchesMondo();

        then(user).seesTransaction(100, "Starbucks", 143, "$");
    }

    @Test
    public void displaysErrorWhenFailingToRetrieveTransactions() {
        given(mondo).isDown();

        when(user).launchesMondo();

        then(user).seesErrorRetrievingTransactions();
    }
}