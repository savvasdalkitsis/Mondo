package com.savvasdalkitsis.mondo.injector.presenter;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.presenter.authentication.AuthenticationPresenter;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;
import com.savvasdalkitsis.mondo.rx.RxTransformers;
import com.savvasdalkitsis.mondo.usecase.authentication.AuthenticationUseCase;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import static com.savvasdalkitsis.mondo.injector.usecase.balance.BalanceUserCaseInjector.balanceUseCase;
import static com.savvasdalkitsis.mondo.injector.usecase.transactions.TransactionsUserCaseInjector.transactionsUseCase;

public class PresentersInjector {
    public static TransactionsPresenter transactionsPresenter() {
        return new TransactionsPresenter(balanceUseCase(), transactionsUseCase());
    }

    public static AuthenticationPresenter authenticationPresenter() {
        return new AuthenticationPresenter(new AuthenticationUseCase() {
            @Override
            public Observable<Response<Void>> authenticate(AuthenticationData authenticationData) {
                return Observable.defer(
                        () -> Observable.just(Response.<Void>error()).delay(5, TimeUnit.SECONDS)
                ).compose(RxTransformers.androidNetworkCall());
            }
        });
    }
}
