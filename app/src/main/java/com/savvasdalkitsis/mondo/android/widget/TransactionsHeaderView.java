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
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.transactions.DayTransactions;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionsHeaderView extends RelativeLayout {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
    private boolean collapsed;
    private TextView date;
    private String today;
    private String yesterday;

    public TransactionsHeaderView(Context context) {
        super(context);
    }

    public TransactionsHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransactionsHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        date = (TextView) findViewById(R.id.view_transaction_header_date);
        today = getContext().getString(R.string.today);
        yesterday = getContext().getString(R.string.yesterday);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (collapsed) {
            setMeasuredDimension(getMeasuredWidth(), 0);
        }
    }

    public void bindTo(DayTransactions dayTransactions) {
        collapsed = false;
        LocalDate now = new LocalDate();
        LocalDate day = dayTransactions.getDate();
        Date date = dayTransactions.getDate().toDate();
        String text = dateFormat.format(date);
        if (now.getYear() == day.getYear()) {
            int dayOfYear = day.getDayOfYear();
            if (now.getDayOfYear() == dayOfYear) {
                text = today;
            } else if (dayOfYear == now.getDayOfYear() - 1) {
                text = yesterday;
            }
        }

        this.date.setText(text);
    }

    public void collapse() {
        collapsed = true;
    }
}
