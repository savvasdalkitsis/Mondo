package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.fakes.FakeMondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class PreferencesCredentialsRepositoryTest {

    private static final String TOKEN = "token";
    private final FakeMondoPreferences mondoPreferences = new FakeMondoPreferences();
    private final PreferencesCredentialsRepository repository = new PreferencesCredentialsRepository(mondoPreferences);

    @Test
    public void readsAccessTokenFromPreferences() {
        savePref(PreferenceKeys.KEY_ACCESS_TOKEN, TOKEN);

        assertThat(repository.getAccessToken(), equalTo(TOKEN));
    }

    @Test
    public void savesAccessTokenInPreferences() {
        repository.saveAccessToken(TOKEN);

        assertPreference(PreferenceKeys.KEY_ACCESS_TOKEN, TOKEN);
    }

    @Test
    public void readsRefreshTokenFromPreferences() {
        savePref(PreferenceKeys.KEY_REFRESH_TOKEN, TOKEN);

        assertThat(repository.getRefreshToken(), equalTo(TOKEN));
    }

    @Test
    public void savesRefreshTokenInPreferences() {
        repository.saveRefreshToken(TOKEN);

        assertPreference(PreferenceKeys.KEY_REFRESH_TOKEN, TOKEN);
    }

    private void savePref(String key, String value) {
        mondoPreferences.putStringPreference(key, value);
    }

    private void assertPreference(String key, String value) {
        assertThat(mondoPreferences.getStringPreference(key), equalTo(value));
    }

}