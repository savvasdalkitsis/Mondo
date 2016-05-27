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
package com.savvasdalkitsis.mondo.injector.infra;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.MainNavigator;
import com.savvasdalkitsis.mondo.infra.MondoAuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.MondoMainNavigator;
import com.savvasdalkitsis.mondo.injector.ApplicationInjector;

public class NavigatorInjector {

    public static AuthenticationNavigator authenticationRepository() {
        return new MondoAuthenticationNavigator(ApplicationInjector.getApplication());
    }

    public static MainNavigator mainRepository() {
        return new MondoMainNavigator(ApplicationInjector.getApplication());
    }
}
