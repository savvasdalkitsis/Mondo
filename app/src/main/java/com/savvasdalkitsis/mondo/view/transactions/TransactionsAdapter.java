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
import android.view.ViewGroup;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.savvasdalkitsis.mondo.infra.transactions.TransactionsGrouper;
import com.savvasdalkitsis.mondo.model.transactions.DayTransactions;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;

import java.util.Collections;
import java.util.List;

public class TransactionsAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<Transaction> transactions = Collections.emptyList();
    private List<DayTransactions> transactionGroups = Collections.emptyList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new TransactionsHeaderItemHolder(parent);
        } else {
            return new TransactionsItemHolder(parent);
        }
    }

    @Override
    public int getSectionCount() {
        return transactionGroups.size();
    }

    @Override
    public int getItemCount(int section) {
        if (section == 0) {
            return 1;
        }
        return transactionGroups.get(section).getTransactions().size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        TransactionsHeaderItemHolder itemHolder = (TransactionsHeaderItemHolder) holder;
        if (section == 0) {
            itemHolder.collapse();
        } else {
            itemHolder.bindTo(transactionGroups.get(section));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {
        TransactionsItemHolder itemHolder = (TransactionsItemHolder) holder;
        if (absolutePosition == 0) {
            itemHolder.setToolbarExpansionMode(true);
            itemHolder.clear();
        } else {
            itemHolder.setToolbarExpansionMode(false);
            itemHolder.bindTo(transactions.get(absolutePosition - 1));
        }
    }

    public void addPage(TransactionsPage transactionsPage) {
        transactions = transactionsPage.getTransactions();
        // the grouping could be performed in the UseCase as well and the view could receive
        // already grouped items
        transactionGroups = TransactionsGrouper.groupTransactions(transactions);
        notifyDataSetChanged();
    }
}
