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
        transactionsView.displayLoadingBalance();
        subscriptions.add(balanceUseCase.getBalance()
                .doOnCompleted(transactionsView::hideBalanceLoading)
                .subscribe(balanceResponse -> {
                    if (!balanceResponse.isError()) {
                        transactionsView.displayBalance(balanceResponse.getData());
                    } else {
                        transactionsView.displayErrorGettingBalance();
                    }
                })
        );
        transactionsView.displayLoadingTransactions();
        subscriptions.add(transactionsUseCase.getTransactions()
                .doOnCompleted(transactionsView::hideTransactionsLoading)
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
