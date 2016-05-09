package com.savvasdalkitsis.mondo.view.authentication;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.presenter.authentication.AuthenticationPresenter;
import com.shazam.android.aspects.base.fragment.AspectSupportFragment;

import static com.savvasdalkitsis.mondo.injector.presenter.PresentersInjector.authenticationPresenter;

public class AuthenticationFragment extends AspectSupportFragment implements AuthenticationView {

    private static final String PARAM_AUTHENTICATION_DATA = "param_authentication_data";

    private final AuthenticationPresenter authenticationPresenter = authenticationPresenter();

    public static Fragment createFragment(AuthenticationData authenticationData) {
        AuthenticationFragment fragment = new AuthenticationFragment();
        fragment.setArguments(new Bundle());
        fragment.setAuthenticationData(authenticationData);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        authenticationPresenter.startPresenting(this, getAuthenticationData());
    }

    private void setAuthenticationData(AuthenticationData authenticationData) {
        getArguments().putSerializable(PARAM_AUTHENTICATION_DATA, authenticationData);
    }

    private AuthenticationData getAuthenticationData() {
        return (AuthenticationData) getArguments().getSerializable(PARAM_AUTHENTICATION_DATA);
    }
}
