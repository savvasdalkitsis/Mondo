package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

import rx.Subscription;

public class TransactionsPresenter {

    private BalanceUseCase balanceUseCase;
    private TransactionsUseCase transactionsUseCase;
    private Subscription subscription;

    public TransactionsPresenter(BalanceUseCase balanceUseCase, TransactionsUseCase transactionsUseCase) {
        this.balanceUseCase = balanceUseCase;
        this.transactionsUseCase = transactionsUseCase;
    }

    public void startPresenting(TransactionsView transactionsView) {
        subscription = balanceUseCase.getBalance()
                .subscribe(balanceResponse -> {
                    if (!balanceResponse.isError()) {
                        transactionsView.displayBalance(balanceResponse.getData());
                    } else {
                        transactionsView.displayError();
                    }
                });
        transactionsUseCase.getTransactions()
                .map(Response::getData)
                .subscribe(transactionsView::displayTransactionsPage);
    }

    public void stopPresenting() {
        subscription.unsubscribe();
    }
}
