/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
