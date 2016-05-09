package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;

public class PreferencesCredentialsRepository implements CredentialsRepository {

    private MondoPreferences mondoPreferences;

    public PreferencesCredentialsRepository(MondoPreferences mondoPreferences) {
        this.mondoPreferences = mondoPreferences;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        mondoPreferences.putStringPreference(PreferenceKeys.KEY_ACCESS_TOKEN, accessToken);
    }

    @Override
    public void saveRefreshToken(String refreshToken) {

    }

    @Override
    public void saveAccountId(String accountId) {

    }

    @Override
    public String getAccessToken() {
        return mondoPreferences.getStringPreference(PreferenceKeys.KEY_ACCESS_TOKEN);
    }

    @Override
    public String getRefreshToken() {
        return mondoPreferences.getStringPreference(PreferenceKeys.KEY_REFRESH_TOKEN);
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
