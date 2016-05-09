package com.savvasdalkitsis.mondo.view.authentication;

import android.net.Uri;
import android.support.v4.app.Fragment;

import com.savvasdalkitsis.mondo.BuildConfig;
import com.shazam.android.aspects.base.fragment.AspectSupportFragment;

public class AuthenticationFragment extends AspectSupportFragment {

    public static Fragment createFragment() {

        Uri authenticationUrl = Uri.parse(BuildConfig.MONDO_AUTH_BASE_URL).buildUpon()
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", BuildConfig.MONDO_CLIENT_ID)
                .appendQueryParameter("redirect_uri", "http://mondotest/redirect")
                .appendQueryParameter("state_token", "asdkjhasl")
                .build();
        return new AuthenticationFragment();
    }
}
