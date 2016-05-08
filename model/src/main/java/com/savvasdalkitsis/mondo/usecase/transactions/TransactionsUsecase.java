package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;

import rx.Observable;

public interface TransactionsUseCase {
    Observable<Response<TransactionsPage>> getTransactions();
}
