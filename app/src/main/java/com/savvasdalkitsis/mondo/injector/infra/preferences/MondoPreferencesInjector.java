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
