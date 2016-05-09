package com.savvasdalkitsis.mondo.view.authentication;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.webkit.WebView;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.BuildConfig;
import com.savvasdalkitsis.mondo.R;
import com.shazam.android.aspects.base.fragment.AspectSupportFragment;

import butterknife.Bind;

@BindLayout(R.layout.fragment_authentication)
public class AuthenticationFragment extends AspectSupportFragment {

    @Bind(R.id.view_web) WebView webView;

    public static Fragment createFragment() {
        return new AuthenticationFragment();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onStart() {
        super.onStart();
        webView.getSettings().setJavaScriptEnabled(true);
        Uri authenticationUrl = Uri.parse(BuildConfig.MONDO_AUTH_BASE_URL).buildUpon()
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", BuildConfig.MONDO_CLIENT_ID)
                .appendQueryParameter("redirect_uri", "http://mondotest/redirect")
                .appendQueryParameter("state_token", "asdkjhasl")
                .build();
        webView.loadUrl(authenticationUrl.toString());
    }
}
