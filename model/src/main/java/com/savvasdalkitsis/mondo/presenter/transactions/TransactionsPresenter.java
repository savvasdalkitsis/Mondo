package com.savvasdalkitsis.mondo.presenter.transactions;

import com.savvasdalkitsis.mondo.usecase.BalanceUseCase;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsView;

public class TransactionsPresenter {

    private BalanceUseCase balanceUseCase;

    public TransactionsPresenter(BalanceUseCase balanceUseCase) {
        this.balanceUseCase = balanceUseCase;
    }

    public void startPresenting(TransactionsView transactionsView) {
        balanceUseCase.getBalance().subscribe(transactionsView::displayBalance);
    }

}
