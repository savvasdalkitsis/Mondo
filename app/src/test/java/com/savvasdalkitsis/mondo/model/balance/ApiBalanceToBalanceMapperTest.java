package com.savvasdalkitsis.mondo.model.balance;

import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class ApiBalanceToBalanceMapperTest {

    private static final String USD = "USD";

    @Test
    public void mapsFullBalanceResponseToBalance() {
        ApiBalanceToBalanceMapper mapper = new ApiBalanceToBalanceMapper();

        ApiBalance apiBalance = ApiBalance.builder()
                .balance(999)
                .spendToday(-666)
                .currency(USD)
                .build();

        Balance expected = Balance.builder()
                .balance(Money.builder().wholeValue(999).currency(USD).build())
                .spentToday(Money.builder().expense(true).wholeValue(666).currency(USD).build())
                .build();


        assertThat(mapper.call(apiBalance), sameBeanAs(expected));
    }
}