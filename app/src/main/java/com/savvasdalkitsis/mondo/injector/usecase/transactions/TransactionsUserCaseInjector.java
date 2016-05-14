package com.savvasdalkitsis.mondo.injector.usecase.transactions;

import com.savvasdalkitsis.mondo.usecase.transactions.MondoTransactionsUseCase;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;

import static com.savvasdalkitsis.mondo.injector.infra.cache.ObservableCacheInjector.observableCache;
import static com.savvasdalkitsis.mondo.injector.infra.date.DateParserInjector.dateParser;
import static com.savvasdalkitsis.mondo.injector.repository.MondoApiInjector.mondoApi;

public class TransactionsUserCaseInjector {
    public static TransactionsUseCase transactionsUseCase() {
        return new MondoTransactionsUseCase(mondoApi(), dateParser(), observableCache());
    }

}
