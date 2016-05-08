package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;

import java.util.HashMap;

public class FakeMondoPreferences implements MondoPreferences {

    private HashMap<String, String> preferences = new HashMap<>();

    public void returningFor(String key, String value) {
        preferences.put(key, value);
    }

    @Override
    public String getStringPreference(String key) {
        return preferences.get(key);
    }

    @Override
    public void putStringPreference(String key, String value) {
        preferences.put(key, value);
    }
}
