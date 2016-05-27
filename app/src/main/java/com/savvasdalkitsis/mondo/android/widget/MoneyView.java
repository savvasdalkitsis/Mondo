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
        // noinspection WrongConstant
        currency.setVisibility(displayStyle.getCurrencyVisibility());
        currency.setText(currencySymbols.getSymbolFor(money.getCurrency()));
        currency.setTextSize(TypedValue.COMPLEX_UNIT_PX, displayStyle.getCurrencyTextSize(getResources()));
        int color = displayStyle.getAmountColor(money, getResources());
        currency.setTextColor(color);
        amount.setTextColor(color);
        amount.setTextSize(TypedValue.COMPLEX_UNIT_PX, displayStyle.getAmountTextSize(getResources()));
        amount.setText(displayStyle.format(money, String.valueOf(money.getWholeValue() / 100d)));
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
