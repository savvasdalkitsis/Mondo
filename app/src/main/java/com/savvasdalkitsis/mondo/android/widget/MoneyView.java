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

    private float largeSize;
    private float smallSize;
    private int smallColor;
    private int largeColor;

    private enum DisplayStyle {
        LARGE(0), SMALL(1);

        private int style;

        DisplayStyle(int style) {
            this.style = style;
        }

        public static DisplayStyle from(int style) {
            for (DisplayStyle displayStyle : values()) {
                if (displayStyle.style == style) {
                    return displayStyle;
                }
            }
            return LARGE;
        }
    }

    private final CurrencySymbols currencySymbols = currencySymbols();
    private TextView currency;
    private TextView amount;
    private DisplayStyle displayStyle = DisplayStyle.LARGE;

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
        smallColor = getResources().getColor(R.color.colorPrimary);
        largeColor = getResources().getColor(android.R.color.white);
        currency = (TextView) findViewById(R.id.view_money_currency);
        amount = (TextView) findViewById(R.id.view_money_amount);
    }

    private void extractAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MoneyView, 0, 0);
            try {
                displayStyle = DisplayStyle.from(a.getInt(R.styleable.MoneyView_displayStyle, 1));
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
        if (displayStyle == DisplayStyle.SMALL) {
            currency.setVisibility(GONE);
            amount.setTextColor(smallColor);
            amount.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallSize);
        } else {
            currency.setVisibility(VISIBLE);
            amount.setTextColor(largeColor);
            amount.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeSize);
        }
    }

    public void markNotAvailable() {
        currency.setText("");
        amount.setText(R.string.not_available);
    }
}
