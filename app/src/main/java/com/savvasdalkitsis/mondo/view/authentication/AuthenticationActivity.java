package com.savvasdalkitsis.mondo.view.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.savvasdalkitsis.butterknifeaspects.aspects.BindLayout;
import com.savvasdalkitsis.mondo.R;
import com.shazam.android.aspects.base.activity.AspectAppCompatActivity;

import butterknife.Bind;

@BindLayout(R.layout.activity_authentication)
public class AuthenticationActivity extends AspectAppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_fragment_container, AuthenticationFragment.createFragment())
                .commit();
    }
}
