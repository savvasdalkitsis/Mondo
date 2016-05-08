package com.savvasdalkitsis.mondo.injector.usecase.transactions;

import com.savvasdalkitsis.mondo.usecase.transactions.MondoTransactionsUseCase;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;

import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class TransactionsUserCaseInjector {
    public static TransactionsUseCase transactionsUseCase() {
        return new MondoTransactionsUseCase(mondoApi());
    }

}
