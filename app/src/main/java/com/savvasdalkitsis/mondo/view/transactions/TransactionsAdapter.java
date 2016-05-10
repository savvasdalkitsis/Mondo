package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;

import java.util.Collections;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Transaction> transactions = Collections.emptyList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new TopSpaceItemHolder(parent);
        }
        return new TransactionsItemHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0) {
            ((TransactionsItemHolder) holder).bindTo(transactions.get(position - 1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return transactions.size() + 1;
    }

    public void addPage(TransactionsPage transactionsPage) {
        transactions = transactionsPage.getTransactions();
        notifyDataSetChanged();
    }
}
