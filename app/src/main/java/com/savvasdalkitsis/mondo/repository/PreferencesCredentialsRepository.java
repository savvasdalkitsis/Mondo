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
