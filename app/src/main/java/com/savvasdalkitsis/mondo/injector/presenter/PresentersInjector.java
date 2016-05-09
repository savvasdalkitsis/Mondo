package com.savvasdalkitsis.mondo.injector.presenter;

import com.savvasdalkitsis.mondo.presenter.authentication.AuthenticationPresenter;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;

import static com.savvasdalkitsis.mondo.injector.usecase.authentication.AuthenticationUserCaseInjector.authenticationUseCase;
import static com.savvasdalkitsis.mondo.injector.usecase.balance.BalanceUserCaseInjector.balanceUseCase;
import static com.savvasdalkitsis.mondo.injector.usecase.transactions.TransactionsUserCaseInjector.transactionsUseCase;

public class PresentersInjector {
    public static TransactionsPresenter transactionsPresenter() {
        return new TransactionsPresenter(balanceUseCase(), transactionsUseCase());
    }

    public static AuthenticationPresenter authenticationPresenter() {
        return new AuthenticationPresenter(authenticationUseCase());
    }
}
