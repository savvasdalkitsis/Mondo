package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;
import rx.functions.Func1;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private Func1<ApiBalance, Balance> mapper;
    private CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall;

    public MondoBalanceUseCase(MondoApi mondoApi, Func1<ApiBalance, Balance> mapper,
                               CachedNetworkCall<ApiBalance, Balance> cachedNetworkCall) {
        this.mondoApi = mondoApi;
        this.mapper = mapper;
        this.cachedNetworkCall = cachedNetworkCall;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return mondoApi.getBalance().compose(cachedNetworkCall.withMapper(mapper, ApiBalance.class));
    }
}