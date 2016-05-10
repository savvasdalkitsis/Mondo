package com.savvasdalkitsis.mondo.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.currency.CurrencySymbols;
import com.savvasdalkitsis.mondo.model.money.Money;

import static com.savvasdalkitsis.mondo.injector.model.currency.CurrencySymbolsInjector.currencySymbols;

public class MoneyView extends RelativeLayout {

    private final CurrencySymbols currencySymbols = currencySymbols();
    private TextView currency;
    private TextView amount;

    public MoneyView(Context context) {
        super(context);
        init();
    }

    public MoneyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoneyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_money, this);
        currency = (TextView) findViewById(R.id.view_money_currency);
        amount = (TextView) findViewById(R.id.view_money_amount);
    }

    public void bindTo(Money money) {
        // this would be formatted by a money formatting class that understands different currencies
        // Out of scope for this exercise
        amount.setText(String.valueOf(money.getWholeValue() / 100d));
        currency.setText(currencySymbols.getSymbolFor(money.getCurrency()));
    }
}
