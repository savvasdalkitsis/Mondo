package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.fakes.FakeMondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PreferencesAccountIdRepositoryTest {

    private static final String ACCOUNT_ID = "account_id";
    private final FakeMondoPreferences preferences = new FakeMondoPreferences();
    private final PreferencesAccountIdRepository repository = new PreferencesAccountIdRepository(preferences);

    @Test
    public void retrievesCurrentAccountIfFromPreferences() {
        preferences.returningFor(PreferenceKeys.KEY_ACCOUNT_ID, ACCOUNT_ID);

        assertThat(repository.getAccountId(), equalTo(ACCOUNT_ID));
    }

    @Test
    public void savesAccountIdInPreferences() {
        repository.saveAccountId(ACCOUNT_ID);

        assertThat(preferences.getStringPreference(PreferenceKeys.KEY_ACCOUNT_ID), equalTo(ACCOUNT_ID));
    }

}