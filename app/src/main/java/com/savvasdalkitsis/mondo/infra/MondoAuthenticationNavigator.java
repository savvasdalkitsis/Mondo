package com.savvasdalkitsis.mondo.infra;

import android.content.Context;

import com.savvasdalkitsis.mondo.MondoApplication;

public class MondoAuthenticationNavigator implements AuthenticationNavigator {

    private final Context context;

    public MondoAuthenticationNavigator(MondoApplication context) {
        this.context = context;
    }

    @Override
    public void navigateToAuthentication() {

    }
}
