package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
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

import rx.Observable;
import rx.functions.Func1;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Collections.singletonList;

public class MondoTransactionsUseCaseTest {

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
    private final MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi, mapper,
            (observable, itemClass) -> observable);

    @Test
    public void retrievesPageFromMondoApi() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder().build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TRANSACTIONS_PAGE)));
    }

    @Test
    public void respondsWithErrorWhenApiErrors() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitTransactionsError();

        subscriber.assertFinishedWithItems(sameBeanAs(Response.error()));
    }

    @Test
    public void respondsWithErrorWhenApiRespondsWithError() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitTransactionsErrorResponse();

        subscriber.assertFinishedWithItems(sameBeanAs(Response.error()));
    }

    private Observable<Response<TransactionsPage>> getTransactions() {
        return useCase.getTransactions();
    }

}