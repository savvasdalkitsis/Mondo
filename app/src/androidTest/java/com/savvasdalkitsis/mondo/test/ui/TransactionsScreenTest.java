package com.savvasdalkitsis.mondo.test.ui;

import com.savvasdalkitsis.mondo.test.R;

import org.junit.Ignore;
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
        given(mondo).cannotBeContacted();

        when(user).launchesMondo();

        then(user).seesErrorContactingMondo();
    }

    @Test
    public void displaysSpentToday() {
        given(user).hasSpentToday(66.6, GBP);

        when(user).launchesMondo();

        then(user).seesSpentToday(66.6, GBP);
    }

    @Test
    @Ignore
    public void displaysUserTransactions() {
        given(user).hasTransactions(R.raw.transactions_100starbucks_33mcdonalds);

        when(user).launchesMondo();

        then(user).seesTransaction(100, "Starbucks");
        then(user).seesTransaction(33, "McDonal's");
    }
}
