package com.savvasdalkitsis.mondo.infra;

import android.content.Context;
import android.content.Intent;

import com.savvasdalkitsis.mondo.MondoApplication;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsActivity;

public class MondoMainNavigator implements MainNavigator {

    private final Context context;

    public MondoMainNavigator(MondoApplication context) {
        this.context = context;
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(context, TransactionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
