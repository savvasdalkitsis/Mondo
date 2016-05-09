package com.savvasdalkitsis.mondo.infra;

import android.content.Context;
import android.content.Intent;

import com.savvasdalkitsis.mondo.MondoApplication;
import com.savvasdalkitsis.mondo.view.authentication.AuthenticationActivity;

public class MondoAuthenticationNavigator implements AuthenticationNavigator {

    private final Context context;

    public MondoAuthenticationNavigator(MondoApplication context) {
        this.context = context;
    }

    @Override
    public void navigateToAuthentication() {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
