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

    @Test
    public void readsAccountIdFromPreferences() {
        savePref(PreferenceKeys.KEY_ACCOUNT_ID, TOKEN);

        assertThat(repository.getAccountId(), equalTo(TOKEN));
    }

    @Test
    public void savesAccountIdInPreferences() {
        repository.saveAccountId(TOKEN);

        assertPreference(PreferenceKeys.KEY_ACCOUNT_ID, TOKEN);
    }

    private void savePref(String key, String value) {
        mondoPreferences.putStringPreference(key, value);
    }

    private void assertPreference(String key, String value) {
        assertThat(mondoPreferences.getStringPreference(key), equalTo(value));
    }

}