package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.usecase.transactions.TransactionsUseCase;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

import rx.subscriptions.CompositeSubscription;

public class TransactionsPresenter {

    private BalanceUseCase balanceUseCase;
    private TransactionsUseCase transactionsUseCase;
    private CompositeSubscription subscriptions;

    public TransactionsPresenter(BalanceUseCase balanceUseCase, TransactionsUseCase transactionsUseCase) {
        this.balanceUseCase = balanceUseCase;
        this.transactionsUseCase = transactionsUseCase;
    }

    public void startPresenting(TransactionsView transactionsView) {
        subscriptions = new CompositeSubscription();
        subscriptions.add(balanceUseCase.getBalance()
                .subscribe(balanceResponse -> {
                    if (!balanceResponse.isError()) {
                        transactionsView.displayBalance(balanceResponse.getData());
                    } else {
                        transactionsView.displayErrorGettingBalance();
                    }
                })
        );
        subscriptions.add(transactionsUseCase.getTransactions()
                .subscribe(transactionsPageResponse -> {
                    if (!transactionsPageResponse.isError()) {
                        transactionsView.displayTransactionsPage(transactionsPageResponse.getData());
                    } else {
                        transactionsView.displayErrorGettingTransactions();
                    }
                })
        );
    }

    public void stopPresenting() {
        subscriptions.unsubscribe();
    }
}
