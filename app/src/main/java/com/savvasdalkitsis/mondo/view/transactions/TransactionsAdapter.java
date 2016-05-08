package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.savvasdalkitsis.mondo.model.transactions.Transaction;
import com.savvasdalkitsis.mondo.model.transactions.TransactionsPage;

import java.util.Collections;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsItemHolder> {

    private List<Transaction> transactions = Collections.emptyList();

    @Override
    public TransactionsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionsItemHolder(parent);
    }

    @Override
    public void onBindViewHolder(TransactionsItemHolder holder, int position) {
        holder.bindTo(transactions.get(position));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void addPage(TransactionsPage transactionsPage) {
        transactions = transactionsPage.getTransactions();
        notifyDataSetChanged();
    }
}
