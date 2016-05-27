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