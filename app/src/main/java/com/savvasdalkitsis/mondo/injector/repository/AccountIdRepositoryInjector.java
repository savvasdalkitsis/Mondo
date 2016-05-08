package com.savvasdalkitsis.mondo.injector.repository;

import com.savvasdalkitsis.mondo.repository.AccountIdRepository;
import com.savvasdalkitsis.mondo.repository.PreferencesAccountIdRepository;

import static com.savvasdalkitsis.mondo.injector.infra.preferences.MondoPreferencesInjector.mondoPreferences;

public class AccountIdRepositoryInjector {

    public static AccountIdRepository accountIdRepository() {
        return new PreferencesAccountIdRepository(mondoPreferences());
    }
}
