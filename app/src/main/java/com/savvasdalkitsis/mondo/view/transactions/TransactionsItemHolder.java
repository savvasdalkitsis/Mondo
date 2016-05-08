package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.infra.image.ImageLoader;
import com.savvasdalkitsis.mondo.model.transactions.Transaction;

import static com.savvasdalkitsis.mondo.injector.infra.image.ImageLoaderInjector.imageLoader;

public class TransactionsItemHolder extends RecyclerView.ViewHolder {

    private final ImageLoader imageLoader = imageLoader();

    private final TextView amount;
    private final TextView merchant;
    private final ImageView logo;

    public TransactionsItemHolder(ViewGroup parent) {
        super(createView(parent));
        logo = (ImageView) itemView.findViewById(R.id.view_transaction_row_logo);
        amount = (TextView) itemView.findViewById(R.id.view_transaction_row_amount);
        merchant = (TextView) itemView.findViewById(R.id.view_transaction_row_merchant);
    }

    private static View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction_row, parent, false);
    }

    public void bindTo(Transaction transaction) {
        amount.setText(String.valueOf(transaction.getAmount()));
        merchant.setText(transaction.getMerchantName());
        imageLoader.load(transaction.getLogoUrl(), logo);
    }
}
