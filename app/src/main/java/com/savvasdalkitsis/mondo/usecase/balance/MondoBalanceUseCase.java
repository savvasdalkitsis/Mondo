package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private Map<String, String> currencySymbolMap;

    public MondoBalanceUseCase(MondoApi mondoApi, Map<String, String> currencySymbolMap) {
        this.mondoApi = mondoApi;
        this.currencySymbolMap = currencySymbolMap;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return mondoApi.getBalance()
                .map(apiBalanceResult -> {
                    retrofit2.Response<ApiBalance> response = apiBalanceResult.response();
                    if (response.isSuccessful()) {
                        return Response.success(Balance.<Balance>builder()
                                .balance(response.body().getBalance())
                                .currencySymbol(currencySymbolMap.get(response.body().getCurrency()))
                                .build());
                    }
                    return Response.<Balance>error();
                })
                .onErrorResumeNext(Observable.just(Response.<Balance>error()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
