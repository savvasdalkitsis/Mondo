package com.savvasdalkitsis.mondo.injector.model.mappers;

import com.savvasdalkitsis.mondo.model.transactions.ApiTransactionsToTransactionsPageMapper;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.injector.infra.date.DateParserInjector.dateParser;

public class MapperInjector {
    public static Func1<ApiTransactions, TransactionsPage> apiTransactionsToTransactionsPageMapper() {
        return new ApiTransactionsToTransactionsPageMapper(dateParser());
    }
}
