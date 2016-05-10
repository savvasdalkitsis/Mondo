package com.savvasdalkitsis.mondo.view.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savvasdalkitsis.mondo.R;

public class TopSpaceItemHolder extends RecyclerView.ViewHolder {
    public TopSpaceItemHolder(ViewGroup parent) {
        super(createView(parent));
    }

    private static View createView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction_empty_top, parent, false);
    }
}
