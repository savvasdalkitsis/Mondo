package com.savvasdalkitsis.mondo;

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
}
