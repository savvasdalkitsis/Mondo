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
package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.android.widget.TransactionsHeaderView;
import com.savvasdalkitsis.mondo.model.transactions.DayTransactions;

public class TransactionsHeaderItemHolder extends RecyclerView.ViewHolder {

    private final TransactionsHeaderView header;

    public TransactionsHeaderItemHolder(ViewGroup parent) {
        super(createView(parent));
        header = (TransactionsHeaderView) itemView.findViewById(R.id.view_transaction_header);
    }

    private static View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction_header, parent, false);
    }

    public void bindTo(DayTransactions dayTransactions) {
        header.bindTo(dayTransactions);
    }

    public void collapse() {
        header.collapse();
    }
}
