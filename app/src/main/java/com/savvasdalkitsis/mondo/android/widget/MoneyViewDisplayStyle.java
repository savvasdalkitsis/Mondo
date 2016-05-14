package com.savvasdalkitsis.mondo.android.widget;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.money.Money;

enum MoneyViewDisplayStyle {

    LARGE(0, View.VISIBLE) {
        @Override
        public int getAmountColor(Money money, Resources resources) {
            return Color.WHITE;
        }

        @Override
        public float getAmountTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_large);
        }

        @Override
        public String format(Money money, String text) {
            return text;
        }

        @Override
        public float getCurrencyTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_small);
        }
    },
    SMALL(1, View.GONE) {
        @Override
        public int getAmountColor(Money money, Resources resources) {
            return resources.getColor(money.isExpense()
                    ? R.color.colorPrimary
                    : R.color.depositGreen);
        }

        @Override
        public float getAmountTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_small);
        }

        @Override
        public String format(Money money, String text) {
            if (!money.isExpense()) {
                return String.format("+%s", text);
            }
            return text;
        }

        @Override
        public float getCurrencyTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_small);
        }
    },
    LOCAL(2, View.VISIBLE) {
        @Override
        public int getAmountColor(Money money, Resources resources) {
            return resources.getColor(R.color.localCurrency);
        }

        @Override
        public float getAmountTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_local);
        }

        @Override
        public String format(Money money, String text) {
            return text;
        }

        @Override
        public float getCurrencyTextSize(Resources resources) {
            return resources.getDimension(R.dimen.balance_size_local_currency);
        }
    };

    private int styleCode;
    private int currencyVisibility;

    MoneyViewDisplayStyle(int styleCode, int currencyVisibility) {
        this.styleCode = styleCode;
        this.currencyVisibility = currencyVisibility;
    }

    public int getStyleCode() {
        return styleCode;
    }

    public static MoneyViewDisplayStyle defaultStyle() {
        return LARGE;
    }

    public static MoneyViewDisplayStyle from(int style) {
        for (MoneyViewDisplayStyle displayStyle : values()) {
            if (displayStyle.styleCode == style) {
                return displayStyle;
            }
        }
        return defaultStyle();
    }

    public int getCurrencyVisibility() {
        return currencyVisibility;
    }

    public abstract int getAmountColor(Money money, Resources resources);
    public abstract float getAmountTextSize(Resources resources);
    public abstract String format(Money money, String text);
    public abstract float getCurrencyTextSize(Resources resources);
}
