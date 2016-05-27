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
package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeCachedNetworkCall;
import com.savvasdalkitsis.mondo.fakes.FakeMapper;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Collections.singletonList;

public class MondoTransactionsUseCaseTest {

    private static final TransactionsPage TRANSACTIONS_PAGE = TransactionsPage.builder()
            .transactions(singletonList(Transaction.builder()
                    .description("transaction")
                    .build()))
            .build();
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final CachedNetworkCall<ApiTransactions, TransactionsPage> cachedNetworkCall = new FakeCachedNetworkCall<>();
    private final HamcrestTestSubscriber<Response<TransactionsPage>> subscriber = new HamcrestTestSubscriber<>();
    private final FakeMapper<ApiTransactions, TransactionsPage> mapper = new FakeMapper<>();
    private final MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi, mapper, cachedNetworkCall);

    @Test
    public void usesCachedNetworkCall() {
        ApiTransactions apiTransactions = ApiTransactions.builder().build();
        mapper.mapping(apiTransactions, TRANSACTIONS_PAGE);

        useCase.getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(apiTransactions);

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TRANSACTIONS_PAGE)));
    }

}