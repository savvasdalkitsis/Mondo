package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;
import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.rx.RxTransformers.applyAndroidSchedulers;
import static com.savvasdalkitsis.mondo.rx.RxTransformers.onErrorToErrorResponse;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private Func1<ApiBalance, Balance> mapper;
    private ObservableCache<ApiBalance> observableCache;

    public MondoBalanceUseCase(MondoApi mondoApi, Func1<ApiBalance, Balance> mapper, ObservableCache<ApiBalance> observableCache) {
        this.mondoApi = mondoApi;
        this.mapper = mapper;
        this.observableCache = observableCache;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return mondoApi.getBalance()
                .compose(observableCache.on(ApiBalance.class))
                .map(apiBalanceResult -> {
                    if (!apiBalanceResult.isError() && apiBalanceResult.response().isSuccessful()) {
                        return Response.success(mapper.call(apiBalanceResult.response().body()));
                    }
                    return Response.<Balance>error();
                })
                .compose(applyAndroidSchedulers())
                .compose(onErrorToErrorResponse());
    }
}