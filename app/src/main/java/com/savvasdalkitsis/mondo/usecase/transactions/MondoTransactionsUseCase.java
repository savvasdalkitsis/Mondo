package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiTransaction;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MondoTransactionsUseCase implements TransactionsUseCase {

    private MondoApi mondoApi;

    public MondoTransactionsUseCase(MondoApi mondoApi) {
        this.mondoApi = mondoApi;
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return mondoApi.getTransactions()
                .map(apiTransactionsResult -> {
                    List<Transaction> transactions = new ArrayList<>();
                    for (ApiTransaction apiTransaction : apiTransactionsResult.response().body().getTransactions()) {
                        transactions.add(Transaction.builder()
                                .amount(Math.abs(apiTransaction.getAmount()))
                                .merchantName(apiTransaction.getMerchant().getName())
                                .build());
                    }
                    return Response.success(TransactionsPage.builder()
                            .transactions(transactions)
                            .build());
                });
    }
}
