package com.savvasdalkitsis.mondo.view.transactions;

import android.view.ViewGroup;
import android.widget.TextView;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.balance.Balance;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;
import com.savvasdalkitsis.mondo.presenter.transactions.TransactionsPresenter;
import com.shazam.android.aspects.base.activity.AspectAppCompatActivity;

import butterknife.Bind;

import static com.savvasdalkitsis.mondo.injector.presenter.PresentersInjector.transactionsPresenter;

@BindLayout(R.layout.activity_transactions)
public class TransactionsActivity extends AspectAppCompatActivity implements TransactionsView {

    private final TransactionsPresenter presenter = transactionsPresenter();

    @Bind(R.id.view_balance) TextView balanceView;
    @Bind(R.id.view_balance_currency) TextView balanceCurrencyView;
    @Bind(R.id.view_spent_today) TextView spentTodayView;
    @Bind(R.id.view_spent_today_currency) TextView spentTodayCurrencyView;
    @Bind(R.id.view_transactions) ViewGroup transactions;

    @Override
    protected void onStart() {
        super.onStart();
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
    public void displayError() {
        balanceView.setText(R.string.error_retrieving_balance);
    }

    @Override
    public void displayTransactionsPage(TransactionsPage transactionsPage) {
        for (Transaction transaction : transactionsPage.getTransactions()) {
            TextView merchant = new TextView(this);
            merchant.setText(transaction.getMerchantName());
            TextView amount = new TextView(this);
            amount.setText(String.valueOf(transaction.getAmount()));
            transactions.addView(merchant);
            transactions.addView(amount);
        }
    }
}
