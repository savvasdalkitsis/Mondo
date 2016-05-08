package com.savvasdalkitsis.mondo.view.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.android.widget.VerticalSpaceItemDecoration;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;
import com.shazam.android.aspects.base.activity.AspectAppCompatActivity;

import butterknife.Bind;

import static com.savvasdalkitsis.mondo.injector.presenter.PresentersInjector.transactionsPresenter;

@BindLayout(R.layout.activity_transactions)
public class TransactionsActivity extends AspectAppCompatActivity implements TransactionsView {

    private final TransactionsPresenter presenter = transactionsPresenter();
    private final TransactionsAdapter transactionsAdapter = new TransactionsAdapter();

    @Bind(R.id.view_balance) TextView balanceView;
    @Bind(R.id.view_balance_currency) TextView balanceCurrencyView;
    @Bind(R.id.view_spent_today) TextView spentTodayView;
    @Bind(R.id.view_spent_today_currency) TextView spentTodayCurrencyView;
    @Bind(R.id.view_transactions) RecyclerView transactions;
    @Bind(R.id.view_coordinator) View coordinator;
    private Snackbar snackbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    protected void onStart() {
        super.onStart();
        transactions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        transactions.addItemDecoration(new VerticalSpaceItemDecoration(getResources()
                .getDimensionPixelSize(R.dimen.transcactions_item_spacing)));
        transactions.setAdapter(transactionsAdapter);
        presenter.startPresenting(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopPresenting();
    }

    @Override
    public void displayBalance(Balance balance) {
        balanceView.setText(String.valueOf(balance.getBalance()));
        balanceCurrencyView.setText(balance.getCurrencySymbol());
        spentTodayView.setText(String.valueOf(balance.getSpentToday()));
        spentTodayCurrencyView.setText(balance.getCurrencySymbol());
    }

    @Override
    public void displayErrorGettingBalance() {
        balanceView.setText(R.string.error_retrieving_balance);
    }

    @Override
    public void displayErrorGettingTransactions() {
        showSnackBar(R.string.error_retrieving_transactions);
    }

    @Override
    public void displayTransactionsPage(TransactionsPage transactionsPage) {
        transactionsAdapter.addPage(transactionsPage);
    }

    private void showSnackBar(@StringRes int message) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        snackbar = Snackbar.make(coordinator, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
