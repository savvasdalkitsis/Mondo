package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.android.widget.MoneyView;
import com.savvasdalkitsis.mondo.infra.image.ImageLoader;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;

import static com.savvasdalkitsis.mondo.injector.infra.image.ImageLoaderInjector.imageLoader;

public class TransactionsItemHolder extends RecyclerView.ViewHolder {

    private final ImageLoader imageLoader = imageLoader();

    private final MoneyView amount;
    private final TextView merchant;
    private final ImageView logo;
    private final View expansion;

    public TransactionsItemHolder(ViewGroup parent) {
        super(createView(parent));
        logo = (ImageView) itemView.findViewById(R.id.view_transaction_row_logo);
        amount = (MoneyView) itemView.findViewById(R.id.view_transaction_row_amount);
        merchant = (TextView) itemView.findViewById(R.id.view_transaction_row_merchant);
        expansion = itemView.findViewById(R.id.view_transaction_row_expansion);
    }

    private static View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction_row, parent, false);
    }

    public void bindTo(Transaction transaction) {
        amount.bindTo(transaction.getAmount());
        merchant.setText(transaction.getDescription());
        imageLoader.load(transaction.getLogoUrl(), logo);
    }

    public void clear() {
        merchant.setText(null);
        amount.clear();
        logo.setImageDrawable(null);
    }

    public void setToolbarExpansionMode(boolean toolbarExpansionMode) {
        expansion.setVisibility(toolbarExpansionMode ? View.VISIBLE : View.GONE);
    }
}
