package com.savvasdalkitsis.mondo.usecase.transactions;

import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiTransactions;

import rx.Observable;
import rx.functions.Func1;

public class MondoTransactionsUseCase implements TransactionsUseCase {

    private MondoApi mondoApi;
    private Func1<ApiTransactions, TransactionsPage> mapper;
    private CachedNetworkCall<ApiTransactions, TransactionsPage> cachedNetworkCall;

    public MondoTransactionsUseCase(MondoApi mondoApi,
                                    Func1<ApiTransactions, TransactionsPage> mapper,
                                    CachedNetworkCall<ApiTransactions, TransactionsPage> cachedNetworkCall) {
        this.mondoApi = mondoApi;
        this.mapper = mapper;
        this.cachedNetworkCall = cachedNetworkCall;
    }

    @Override
    public Observable<Response<TransactionsPage>> getTransactions() {
        return mondoApi.getTransactions().compose(cachedNetworkCall.withMapper(mapper, ApiTransactions.class));
    }
}