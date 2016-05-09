package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.fakes.FakeMondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class PreferencesCredentialsRepositoryTest {

    private final FakeMondoPreferences mondoPreferences = new FakeMondoPreferences();
    private final PreferencesCredentialsRepository repository = new PreferencesCredentialsRepository(mondoPreferences);

    @Test
    public void readsAccessTokenFromPreferences() {
        mondoPreferences.putStringPreference(PreferenceKeys.KEY_ACCESS_TOKEN, "access_token");

        assertThat(repository.getAccessToken(), equalTo("access_token"));
    }

}