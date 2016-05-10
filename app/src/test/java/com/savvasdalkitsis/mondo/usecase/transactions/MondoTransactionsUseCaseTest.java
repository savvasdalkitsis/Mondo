package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiMerchant;
import com.savvasdalkitsis.mondo.repository.model.ApiTransaction;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import rx.Observable;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class MondoTransactionsUseCaseTest {

    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    @Rule public TestRule timeout = Timeout.seconds(3);
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final HamcrestTestSubscriber<Response<TransactionsPage>> subscriber = new HamcrestTestSubscriber<>();
    private final MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi);

    @Test
    public void retrievesPageFromMondoApi() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder()
                .transactions(singletonList(ApiTransaction.builder()
                        .amount(-100)
                        .merchant(ApiMerchant.builder()
                                .name("merchant1")
                                .logo("logo1")
                                .build())
                        .build()))
                .build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .merchantName("merchant1")
                        .logoUrl("logo1")
                        .amount(100)
                        .build()))
                .build())));
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

    @Test
    public void allowsTransactionsWithoutMerchant() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder()
                .transactions(singletonList(ApiTransaction.builder()
                        .merchant(null)
                        .amount(-100)
                        .build()))
                .build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TransactionsPage.builder()
                .transactions(singletonList(Transaction.builder()
                        .amount(100)
                        .build()))
                .build())));
    }

    @Test
    public void presentsTransactionsInReverseOrder() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder()
                .transactions(asList(
                        ApiTransaction.builder().amount(-1).build(),
                        ApiTransaction.builder().amount(-2).build()))
                .build());

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success(TransactionsPage.builder()
                .transactions(asList(
                        Transaction.builder().amount(2).build(),
                        Transaction.builder().amount(1).build()
                ))
                .build())));
    }

    private Observable<Response<TransactionsPage>> getTransactions() {
        return useCase.getTransactions();
    }

}