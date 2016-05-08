package com.savvasdalkitsis.mondo.infra.preferences;

public interface MondoPreferences {

    String getStringPreference(String key);
    void putStringPreference(String key, String value);
}
