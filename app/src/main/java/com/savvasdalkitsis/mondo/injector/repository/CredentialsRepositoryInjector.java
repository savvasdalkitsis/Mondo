package com.savvasdalkitsis.mondo.injector.repository;

import com.savvasdalkitsis.mondo.repository.CredentialsRepository;
import com.savvasdalkitsis.mondo.repository.PreferencesCredentialsRepository;

import static com.savvasdalkitsis.mondo.injector.infra.preferences.MondoPreferencesInjector.mondoPreferences;

public class CredentialsRepositoryInjector {

    public static CredentialsRepository credentialsRepository() {
        return new PreferencesCredentialsRepository(mondoPreferences());
    }
}
