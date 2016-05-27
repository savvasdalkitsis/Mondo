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

@BindLayout(R.layout.fragment_authentication_web)
public class AuthenticationWebFragment extends AspectSupportFragment {

    @Bind(R.id.view_web) WebView webView;

    public static Fragment createFragment() {
        return new AuthenticationWebFragment();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onStart() {
        super.onStart();
        webView.getSettings().setJavaScriptEnabled(true);
        Uri authenticationUrl = Uri.parse(BuildConfig.MONDO_AUTH_BASE_URL).buildUpon()
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", BuildConfig.MONDO_CLIENT_ID)
                .appendQueryParameter("redirect_uri", BuildConfig.API_CALL_REDIRECT_URI)
                .appendQueryParameter("state_token", "asdkjhasl")
                .build();
        webView.loadUrl(authenticationUrl.toString());
    }
}
