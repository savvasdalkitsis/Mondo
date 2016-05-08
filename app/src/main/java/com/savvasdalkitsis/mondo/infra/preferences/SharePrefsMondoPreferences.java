package com.savvasdalkitsis.mondo.infra.preferences;

import android.content.SharedPreferences;

// This class is not tested here. Normally, a Robolectric integration test would be used to verify
// its behaviour but I feel this is outside the scope of this exercise
public class SharePrefsMondoPreferences implements MondoPreferences {

    private final SharedPreferences preferences;

    public SharePrefsMondoPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public String getStringPreference(String key) {
        return preferences.getString(key, null);
    }

    @Override
    public void putStringPreference(String key, String value) {
        preferences.edit()
                .putString(key, value)
                .apply();
    }
}
