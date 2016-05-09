package com.savvasdalkitsis.mondo.view.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.shazam.android.aspects.base.activity.AspectAppCompatActivity;

import butterknife.Bind;

@BindLayout(R.layout.activity_authentication)
public class AuthenticationActivity extends AspectAppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Fragment fragment = AuthenticationWebFragment.createFragment();
        loadFragment(fragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        loadFragment(AuthenticationFragment.createFragment(AuthenticationData.builder()
                .code(intent.getData().getQueryParameter("code"))
                .state(intent.getData().getQueryParameter("state"))
                .build()));
    }

    private int loadFragment(Fragment fragment) {
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_fragment_container, fragment)
                .commit();
    }
}
