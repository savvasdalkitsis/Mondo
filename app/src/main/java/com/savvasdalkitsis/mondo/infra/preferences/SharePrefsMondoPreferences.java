/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
