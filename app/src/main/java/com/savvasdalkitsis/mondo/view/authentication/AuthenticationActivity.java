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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.infra.MainNavigator;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.shazam.android.aspects.base.activity.AspectAppCompatActivity;

import butterknife.Bind;

import static com.savvasdalkitsis.mondo.injector.infra.NavigatorInjector.mainRepository;

@BindLayout(R.layout.activity_authentication)
public class AuthenticationActivity extends AspectAppCompatActivity implements AuthenticationListener {

    private final MainNavigator mainNavigator = mainRepository();
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        loadFragment(AuthenticationWebFragment.createFragment());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Uri data = intent.getData();
        if (data != null) {
            loadFragment(AuthenticationFragment.createFragment(AuthenticationData.builder()
                    .code(data.getQueryParameter("code"))
                    .state(data.getQueryParameter("state"))
                    .build()));
        }
    }

    private int loadFragment(Fragment fragment) {
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_fragment_container, fragment)
                .commit();
    }

    @Override
    public void onRetryAuthentication() {
        loadFragment(AuthenticationWebFragment.createFragment());
    }

    @Override
    public void onAuthenticationSuccess() {
        finish();
        mainNavigator.navigateToMainScreen();
    }
}
