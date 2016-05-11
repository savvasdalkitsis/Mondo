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
