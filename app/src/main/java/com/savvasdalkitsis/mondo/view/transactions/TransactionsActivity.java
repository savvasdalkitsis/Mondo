package com.savvasdalkitsis.mondo.view.transactions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.android.widget.MondoToolbar;
import com.savvasdalkitsis.mondo.android.widget.MoneyView;
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

    @Bind(R.id.view_balance) MoneyView balanceView;
    @Bind(R.id.view_spent_today) MoneyView spentTodayView;
    @Bind(R.id.view_transactions) RecyclerView transactions;
    @Bind(R.id.toolbar) MondoToolbar toolbar;
    @Bind(R.id.view_progress) ProgressBar balanceProgressBar;
    @Bind(android.R.id.content) View root;
    private Snackbar errorSnackbar;
    private Snackbar loadingSnackbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        transactions.setLayoutManager(layoutManager);
        transactions.setAdapter(transactionsAdapter);
        transactions.addOnScrollListener(toolbar.scrollListener(layoutManager));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.stopPresenting();
        presenter.startPresenting(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopPresenting();
    }

    @Override
    public void displayBalance(Balance balance) {
        balanceView.bindTo(balance.getBalance());
        spentTodayView.bindTo(balance.getSpentToday());
    }

    @Override
    public void displayErrorGettingBalance() {
        balanceProgressBar.setVisibility(View.GONE);
        balanceView.markNotAvailable();
    }

    @Override
    public void displayErrorGettingTransactions() {
        errorSnackbar = showSnackBar(R.string.error_retrieving_transactions);
    }

    @Override
    public void displayTransactionsPage(TransactionsPage transactionsPage) {
        transactionsAdapter.addPage(transactionsPage);
    }

    @Override
    public void displayLoadingBalance() {
        balanceProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayLoadingTransactions() {
        loadingSnackbar = showSnackBar(R.string.loading_transactions);
    }

    @NonNull
    private Snackbar showSnackBar(@StringRes int message) {
        hideSnackBars();
        Snackbar snackbar = Snackbar.make(root, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        return snackbar;
    }

    @Override
    public void hideBalanceLoading() {
        balanceProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideTransactionsLoading() {
        hide(loadingSnackbar);
    }

    private void hideSnackBars() {
        hide(errorSnackbar);
        hide(loadingSnackbar);
    }

    private void hide(Snackbar snackbar) {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
}
