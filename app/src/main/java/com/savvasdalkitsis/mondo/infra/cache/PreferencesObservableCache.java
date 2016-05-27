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
package com.savvasdalkitsis.mondo.infra.cache;

import com.google.gson.Gson;
import com.savvasdalkitsis.mondo.infra.log.Logger;
import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;
import com.savvasdalkitsis.mondo.util.StringUtils;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

import static retrofit2.Response.success;
import static retrofit2.adapter.rxjava.Result.response;
import static rx.Observable.concat;
import static rx.Observable.defer;
import static rx.Observable.empty;
import static rx.Observable.just;

public class PreferencesObservableCache<T> implements ObservableCache<T> {

    private final Gson gson;
    private final MondoPreferences mondoPreferences;

    public PreferencesObservableCache(MondoPreferences mondoPreferences, Gson gson) {
        this.gson = gson;
        this.mondoPreferences = mondoPreferences;
    }

    @Override
    public Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass) {
        return observable -> concat(cachedItems(itemClass), observable)
                .doOnNext(item -> save(item, itemClass));
    }

    private Observable<Result<T>> cachedItems(Class<T> itemClass) {
        return defer(() -> {
            String item = mondoPreferences.getStringPreference(getPreferenceKey(itemClass));
            if (StringUtils.isNotEmptyNorNull(item)) {
                try {
                    T t = gson.fromJson(item, itemClass);
                    return just(response(success(t)));
                } catch (Exception e) {
                    Logger.error("ObservableCache", "Error reading cached item", e);
                }
            }
            return empty();
        });
    }

    private void save(Result<T> result, Class<T> itemClass) {
        if (!result.isError() && result.response().isSuccessful()) {
            mondoPreferences.putStringPreference(getPreferenceKey(itemClass), gson.toJson(result.response().body()));
        }
    }

    private String getPreferenceKey(Class<T> itemClass) {
        return String.format(PreferenceKeys.KEY_OBSERVABLE_CACHE, itemClass.getName());
    }
}
