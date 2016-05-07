package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

import rx.Subscription;

public class TransactionsPresenter {

    private BalanceUseCase balanceUseCase;
    private Subscription subscription;

    public TransactionsPresenter(BalanceUseCase balanceUseCase) {
        this.balanceUseCase = balanceUseCase;
    }

    public void startPresenting(TransactionsView transactionsView) {
        subscription = balanceUseCase.getBalance()
                .map(Response::getData)
                .subscribe(transactionsView::displayBalance);
    }

    public void stopPresenting() {
        subscription.unsubscribe();
    }
}
