package com.savvasdalkitsis.mondo.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.currency.CurrencySymbols;
import com.savvasdalkitsis.mondo.model.money.Money;

import static com.savvasdalkitsis.mondo.injector.model.currency.CurrencySymbolsInjector.currencySymbols;

public class MoneyView extends RelativeLayout {

    private final CurrencySymbols currencySymbols = currencySymbols();
    private float largeSize;
    private float smallSize;
    private int largeColor;
    private int smallColorExpense;
    private int smallColorDeposit;
    private TextView currency;
    private TextView amount;
    private MoneyViewDisplayStyle displayStyle = MoneyViewDisplayStyle.defaultStyle();

    public MoneyView(Context context) {
        super(context);
        init(null);
    }

    public MoneyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MoneyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_money, this);
        extractAttrs(attrs);
        largeSize = getResources().getDimension(R.dimen.balance_size_large);
        smallSize = getResources().getDimension(R.dimen.balance_size_small);
        smallColorExpense = getResources().getColor(R.color.colorPrimary);
        smallColorDeposit = getResources().getColor(R.color.deposit_green);
        largeColor = getResources().getColor(android.R.color.white);
        currency = (TextView) findViewById(R.id.view_money_currency);
        amount = (TextView) findViewById(R.id.view_money_amount);
    }

    private void extractAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MoneyView, 0, 0);
            try {
                displayStyle = MoneyViewDisplayStyle.from(
                        a.getInt(R.styleable.MoneyView_displayStyle, MoneyViewDisplayStyle.defaultStyle().getStyleCode()));
            } finally {
                a.recycle();
            }
        }
    }

    public void bindTo(Money money) {
        // this would be formatted by a money formatting class that understands different currencies
        // Out of scope for this exercise
        amount.setText(String.valueOf(money.getWholeValue() / 100d));
        currency.setText(currencySymbols.getSymbolFor(money.getCurrency()));
        if (displayStyle == MoneyViewDisplayStyle.SMALL) {
            currency.setVisibility(GONE);
            amount.setTextColor(money.isExpense() ? smallColorExpense : smallColorDeposit);
            amount.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallSize);
            if (!money.isExpense()) {
                amount.setText(String.format("+%s", amount.getText().toString()));
            }
        } else {
            currency.setVisibility(VISIBLE);
            amount.setTextColor(largeColor);
            amount.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeSize);
        }
    }

    public void clear() {
        currency.setText(null);
        amount.setText(null);
    }

    public void markNotAvailable() {
        currency.setText("");
        amount.setText(R.string.not_available);
    }
}
