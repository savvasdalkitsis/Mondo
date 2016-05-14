package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.fakes.FakeObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import rx.functions.Func1;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Collections.singletonList;

public class MondoTransactionsUseCaseCacheTest {

    private static final TransactionsPage TRANSACTIONS_PAGE = TransactionsPage.builder()
            .transactions(singletonList(Transaction.builder()
                    .description("transaction")
                    .build()))
            .build();
    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(3);
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final HamcrestTestSubscriber<Response<TransactionsPage>> subscriber = new HamcrestTestSubscriber<>();
    private final Func1<ApiTransactions, TransactionsPage> mapper = apiTransactions -> TRANSACTIONS_PAGE;
    private final FakeObservableCache<ApiTransactions> observableCache = new FakeObservableCache<>();
    private final MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi, mapper,
            observableCache);

    @Test
    public void usesCachedVersionOfApi() {
        useCase.getTransactions().subscribe(subscriber);

        observableCache.emitSuccessfulResult(ApiTransactions.builder().build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TRANSACTIONS_PAGE)));
    }
}