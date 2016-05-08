package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiMerchant;
import com.savvasdalkitsis.mondo.repository.model.ApiTransaction;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;

public class MondoTransactionsUseCaseTest {

    @Test
    public void retrievesPageFromMondoApi() {
        FakeMondoApi mondoApi = new FakeMondoApi();
        HamcrestTestSubscriber<Response<TransactionsPage>> subscriber = new HamcrestTestSubscriber<>();
        MondoTransactionsUseCase useCase = new MondoTransactionsUseCase(mondoApi);

        useCase.getTransactions().subscribe(subscriber);

        mondoApi.emitSuccessfulTransactionPage(ApiTransactions.builder()
                .transactions(asList(ApiTransaction.builder()
                        .amount(-100)
                        .merchant(ApiMerchant.builder()
                                .name("merchant1")
                                .build())
                        .build(), ApiTransaction.builder()
                        .amount(-200)
                        .merchant(ApiMerchant.builder()
                                .name("merchant2")
                                .build())
                        .build()))
                .build());

        subscriber.assertFinishedWithItem(sameBeanAs(Response.success(TransactionsPage.builder()
                .transactions(asList(Transaction.builder()
                        .merchantName("merchant1")
                        .amount(100)
                        .build(), Transaction.builder()
                        .merchantName("merchant2")
                        .amount(200)
                        .build()))
                .build())));
    }

}