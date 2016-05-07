package com.savvasdalkitsis.mondo.injector.presenter;

import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;

import static com.savvasdalkitsis.mondo.injector.usecase.balance.BalanceUserCaseInjector.balanceUseCase;

public class PresentersInjector {
    public static TransactionsPresenter transactionsPresenter() {
        return new TransactionsPresenter(balanceUseCase());
    }
}
