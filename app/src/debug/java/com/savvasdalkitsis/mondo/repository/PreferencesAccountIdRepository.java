package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;

public class PreferencesAccountIdRepository implements AccountIdRepository {

    private MondoPreferences preferences;

    public PreferencesAccountIdRepository(MondoPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public String getAccountId() {
        return preferences.getStringPreference(PreferenceKeys.KEY_ACCOUNT_ID);
    }

    @Override
    public void saveAccountId(String accountId) {
        preferences.putStringPreference(PreferenceKeys.KEY_ACCOUNT_ID, accountId);
    }
}
