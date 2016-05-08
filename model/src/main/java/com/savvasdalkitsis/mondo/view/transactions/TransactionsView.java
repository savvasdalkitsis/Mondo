package com.savvasdalkitsis.mondo.view.transactions;

import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;

public interface TransactionsView {

    void displayBalance(Balance balance);
    void displayErrorGettingBalance();
    void displayErrorGettingTransactions();
    void displayTransactionsPage(TransactionsPage transactionsPage);
}
