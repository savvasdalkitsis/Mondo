package com.savvasdalkitsis.mondo.injector.presenter;

import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;

public class PresentersInjector {
    public static TransactionsPresenter transactionsPresenter() {
        return new TransactionsPresenter(null);
    }
}
