package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;

import static com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys.KEY_ACCESS_TOKEN;
import static com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys.KEY_ACCOUNT_ID;
import static com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys.KEY_REFRESH_TOKEN;

public class PreferencesCredentialsRepository implements CredentialsRepository {

    private MondoPreferences mondoPreferences;

    public PreferencesCredentialsRepository(MondoPreferences mondoPreferences) {
        this.mondoPreferences = mondoPreferences;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        savePref(accessToken, KEY_ACCESS_TOKEN);
    }

    @Override
    public void saveRefreshToken(String refreshToken) {
        savePref(refreshToken, KEY_REFRESH_TOKEN);
    }

    @Override
    public void saveAccountId(String accountId) {
        savePref(accountId, KEY_ACCOUNT_ID);
    }

    @Override
    public String getAccessToken() {
        return pref(KEY_ACCESS_TOKEN);
    }

    @Override
    public String getRefreshToken() {
        return pref(KEY_REFRESH_TOKEN);
    }

    @Override
    public String getAccountId() {
        return pref(KEY_ACCOUNT_ID);
    }

    private void savePref(String accountId, String keyAccountId) {
        mondoPreferences.putStringPreference(keyAccountId, accountId);
    }

    private String pref(String keyAccountId) {
        return mondoPreferences.getStringPreference(keyAccountId);
    }
}
