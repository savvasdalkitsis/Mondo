package com.savvasdalkitsis.mondo.usecase.balance;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.currency.CurrencySymbols;
import com.savvasdalkitsis.mondo.repository.MondoApi;
import com.savvasdalkitsis.mondo.repository.model.ApiBalance;
import com.savvasdalkitsis.mondo.rx.RxTransformers;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;

import rx.Observable;

public class MondoBalanceUseCase implements BalanceUseCase {

    private MondoApi mondoApi;
    private CurrencySymbols currencySymbols;

    public MondoBalanceUseCase(MondoApi mondoApi, CurrencySymbols currencySymbols) {
        this.mondoApi = mondoApi;
        this.currencySymbols = currencySymbols;
    }

    @Override
    public Observable<Response<Balance>> getBalance() {
        return mondoApi.getBalance()
                .map(apiBalanceResult -> {
                    retrofit2.Response<ApiBalance> response = apiBalanceResult.response();
                    if (response.isSuccessful()) {
                        ApiBalance apiBalance = response.body();
                        return Response.success(Balance.<Balance>builder()
                                .balance(apiBalance.getBalance())
                                .spentToday(apiBalance.getSpentToday())
                                .currencySymbol(currencySymbols.getSymbolFor(apiBalance.getCurrency()))
                                .build());
                    }
                    return Response.<Balance>error();
                })
                .compose(RxTransformers.androidNetworkCall())
                .onErrorResumeNext(Observable.just(Response.<Balance>error()));
    }

}