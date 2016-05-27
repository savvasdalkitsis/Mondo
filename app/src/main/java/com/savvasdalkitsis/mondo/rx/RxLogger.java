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
package com.savvasdalkitsis.mondo.rx;

import com.savvasdalkitsis.mondo.infra.log.Logger;

import rx.plugins.DebugHook;
import rx.plugins.DebugNotificationListener;
import rx.plugins.RxJavaPlugins;

public class RxLogger {

    public static void logRx() {
        RxJavaPlugins.getInstance().registerObservableExecutionHook(new DebugHook<>(new DebugNotificationListener<Object>() {
            @Override
            public void error(Object context, Throwable e) {
                Logger.error("RxLog", "onError()", e);
                super.error(context, e);
            }
        }));
    }
}
