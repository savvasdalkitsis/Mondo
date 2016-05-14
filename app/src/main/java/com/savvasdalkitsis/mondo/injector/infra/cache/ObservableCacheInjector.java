package com.savvasdalkitsis.mondo.injector.infra.cache;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.infra.cache.PreferencesObservableCache;

import static com.savvasdalkitsis.mondo.injector.infra.external.gson.GsonInjector.gson;
import static com.savvasdalkitsis.mondo.injector.infra.preferences.MondoPreferencesInjector.mondoPreferences;

public class ObservableCacheInjector {

    public static <T> ObservableCache<T> observableCache() {
        return new PreferencesObservableCache<>(mondoPreferences(), gson());
    }

}
