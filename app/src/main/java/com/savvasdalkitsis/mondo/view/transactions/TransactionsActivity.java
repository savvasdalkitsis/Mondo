package com.savvasdalkitsis.mondo.view.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;

import static com.savvasdalkitsis.mondo.injector.presenter.PresentersInjector.transactionsPresenter;

public class TransactionsActivity extends AppCompatActivity implements TransactionsView {

    private final TransactionsPresenter presenter = transactionsPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this);
    }

    @Override
    public void displayBalance(Balance balance) {

    }
}
