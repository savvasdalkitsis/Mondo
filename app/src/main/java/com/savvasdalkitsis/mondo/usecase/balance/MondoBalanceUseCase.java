package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.money.Money;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.rx.RxTransformers;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private ObservableCache<ApiBalance> observableCache;

    public MondoBalanceUseCase(MondoApi mondoApi, ObservableCache<ApiBalance> observableCache) {
        this.mondoApi = mondoApi;
        this.observableCache = observableCache;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return observableCache.cache(mondoApi.getBalance(), ApiBalance.class)
                .map(apiBalanceResult -> {
                    retrofit2.Response<ApiBalance> response = apiBalanceResult.response();
                    if (!apiBalanceResult.isError() && response.isSuccessful()) {
                        ApiBalance apiBalance = response.body();
                        String currency = apiBalance.getCurrency();
                        return Response.success(Balance.<Balance>builder()
                                .balance(Money.builder().wholeValue(apiBalance.getBalance()).currency(currency).build())
                                .spentToday(Money.builder()
                                        .wholeValue(Math.abs(apiBalance.getSpendToday()))
                                        .expense(apiBalance.getSpendToday() < 0)
                                        .currency(currency)
                                        .build())
                                .build());
                    }
                    return Response.<Balance>error();
                })
                .compose(RxTransformers.androidNetworkCall())
                .compose(RxTransformers.mapErrorToErrorResponse());
    }
}