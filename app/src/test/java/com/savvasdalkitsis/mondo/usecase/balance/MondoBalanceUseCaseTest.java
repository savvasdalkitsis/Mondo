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
package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.fakes.FakeCachedNetworkCall;
import com.savvasdalkitsis.mondo.fakes.FakeMapper;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class MondoBalanceUseCaseTest {

    private static final Balance BALANCE = Balance.builder().balance(Money.builder().currency("$").build()).build();

    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final FakeMapper<ApiBalance, Balance> mapper = new FakeMapper<>();
    private final CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall = new FakeCachedNetworkCall<>();
    private final MondoBalanceUseCase useCase = new MondoBalanceUseCase(mondoApi, mapper, cachedNetworkCall);
    private final HamcrestTestSubscriber<Response<Balance>> subscriber = new HamcrestTestSubscriber<>();

    @Test
    public void usesCachedNetworkCall() {
        ApiBalance apiBalance = ApiBalance.builder().build();
        mapper.mapping(apiBalance, BALANCE);

        useCase.getBalance().subscribe(subscriber);

        mondoApi.emitSuccessfulBalance(apiBalance);

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(BALANCE)));
    }

}