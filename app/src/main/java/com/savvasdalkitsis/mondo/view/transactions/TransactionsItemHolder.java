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
    private final MoneyView localCurrencyMoney;

    public TransactionsItemHolder(ViewGroup parent) {
        super(createView(parent));
        logo = (ImageView) itemView.findViewById(R.id.view_transaction_row_logo);
        amount = (MoneyView) itemView.findViewById(R.id.view_transaction_row_amount);
        merchant = (TextView) itemView.findViewById(R.id.view_transaction_row_merchant);
        expansion = itemView.findViewById(R.id.view_transaction_row_expansion);
        localCurrencyMoney = (MoneyView) itemView.findViewById(R.id.view_transaction_row_local_currency_amount);
    }

    private static View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction_row, parent, false);
    }

    public void bindTo(Transaction transaction) {
        amount.bindTo(transaction.getAmount());
        merchant.setText(transaction.getDescription());
        imageLoader.load(transaction.getLogoUrl(), logo);
        if (transaction.getAmount().sameCurrencyAs(transaction.getAmountInLocalCurrency())) {
            localCurrencyMoney.setVisibility(View.GONE);
        } else {
            localCurrencyMoney.setVisibility(View.VISIBLE);
            localCurrencyMoney.bindTo(transaction.getAmountInLocalCurrency());
        }
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
