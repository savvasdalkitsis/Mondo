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
        given(user).hasBalance(123.4, GBP);

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
        given(user).hasSpentToday(66.6, GBP);

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
    public void displaysErrorWhenFailingToRetrieveTransactions() {
        given(mondo).isDown();

        when(user).launchesMondo();

        then(user).seesErrorRetrievingTransactions();
    }
}