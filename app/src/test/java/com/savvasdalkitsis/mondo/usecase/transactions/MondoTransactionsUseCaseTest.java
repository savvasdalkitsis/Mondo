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

import rx.Observable;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;

public class MondoTransactionsUseCaseTest {

    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final HamcrestTestSubscriber<Response<TransactionsPage>> subscriber = new HamcrestTestSubscriber<>();
    private final MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi);

    @Test
    public void retrievesPageFromMondoApi() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder()
                .transactions(asList(ApiTransaction.builder()
                        .amount(-100)
                        .merchant(ApiMerchant.builder()
                                .name("merchant1")
                                .logo("logo1")
                                .build())
                        .build(), ApiTransaction.builder()
                        .amount(-200)
                        .merchant(ApiMerchant.builder()
                                .name("merchant2")
                                .logo("logo2")
                                .build())
                        .build()))
                .build());

        subscriber.assertFinishedWithItem(sameBeanAs(Response.success(TransactionsPage.builder()
                .transactions(asList(Transaction.builder()
                        .merchantName("merchant1")
                        .logoUrl("logo1")
                        .amount(100)
                        .build(), Transaction.builder()
                        .merchantName("merchant2")
                        .logoUrl("logo2")
                        .amount(200)
                        .build()))
                .build())));
    }

    @Test
    public void respondsWithErrorWhenApiErrors() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitTransactionsError();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.error()));
    }

    @Test
    public void respondsWithErrorWhenApiRespondsWithError() {
        getTransactions().subscribe(subscriber);

        mondoApi.emitTransactionsErrorResponse();

        subscriber.assertFinishedWithItem(sameBeanAs(Response.error()));
    }

    private Observable<Response<TransactionsPage>> getTransactions() {
        return useCase.getTransactions();
    }

}