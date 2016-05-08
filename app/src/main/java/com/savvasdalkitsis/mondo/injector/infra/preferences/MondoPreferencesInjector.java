package com.savvasdalkitsis.mondo.injector.infra.preferences;

import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.SharePrefsMondoPreferences;
import com.savvasdalkitsis.mondo.injector.ApplicationInjector;

public class MondoPreferencesInjector {

    public static MondoPreferences mondoPreferences() {
        return new SharePrefsMondoPreferences(ApplicationInjector.getApplication()
                .getSharedPreferences("mondo_preferences", 0));
    }
}
