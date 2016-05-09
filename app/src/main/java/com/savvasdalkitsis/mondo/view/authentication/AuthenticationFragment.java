package com.savvasdalkitsis.mondo.view.authentication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.presenter.authentication.AuthenticationPresenter;
import com.shazam.android.aspects.base.fragment.AspectSupportFragment;

import butterknife.Bind;

import static com.savvasdalkitsis.mondo.injector.presenter.PresentersInjector.authenticationPresenter;

@BindLayout(R.layout.fragment_authentication)
public class AuthenticationFragment extends AspectSupportFragment implements AuthenticationView {

    private static final String PARAM_AUTHENTICATION_DATA = "param_authentication_data";

    private final AuthenticationPresenter authenticationPresenter = authenticationPresenter();

    @Bind(R.id.view_progress)
    View progress;
    @Bind(R.id.view_authentication_title)
    TextView authenticationTitle;
    @Bind(R.id.view_retry)
    Button retry;
    private AuthenticationListener authenticationListener;

    public static Fragment createFragment(AuthenticationData authenticationData) {
        AuthenticationFragment fragment = new AuthenticationFragment();
        fragment.setArguments(new Bundle());
        fragment.setAuthenticationData(authenticationData);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        authenticationListener = (AuthenticationListener) activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retry.setOnClickListener(v -> authenticationListener.onRetryAuthentication());
    }

    @Override
    public void onStart() {
        super.onStart();
        authenticationPresenter.startPresenting(this, getAuthenticationData());
    }

    @Override
    public void onStop() {
        super.onStop();
        authenticationPresenter.stopPresenting();
    }

    @Override
    public void displayLoading() {
        authenticationTitle.setText(R.string.logging_in);
        progress.setVisibility(View.VISIBLE);
        retry.setVisibility(View.INVISIBLE);
    }

    @Override
    public void successfulAuthentication() {
        authenticationTitle.setText(R.string.logged_in);
        progress.setVisibility(View.INVISIBLE);
        authenticationListener.onAuthenticationSuccess();
    }

    @Override
    public void errorAuthenticating() {
        authenticationTitle.setText(R.string.error_logging_in);
        retry.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    private void setAuthenticationData(AuthenticationData authenticationData) {
        getArguments().putSerializable(PARAM_AUTHENTICATION_DATA, authenticationData);
    }

    private AuthenticationData getAuthenticationData() {
        return (AuthenticationData) getArguments().getSerializable(PARAM_AUTHENTICATION_DATA);
    }
}
