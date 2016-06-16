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
package com.savvasdalkitsis.mondo.fakes;

import android.support.annotation.NonNull;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.subjects.ReplaySubject;

public class FakeObservableCache<T> implements ObservableCache<T> {

    private final Map<Class<?>, ReplaySubject<Result<T>>> subjects = new HashMap<>();

    @Override
    public Observable.Transformer<Result<T>, Result<T>> on(Class<T> itemClass) {
        return observable -> getOrCreateSubjectFor(itemClass);
    }

    public void emitsSuccessfulResultFor(Class<T> itemClass, T item) {
        ReplaySubject<Result<T>> subject = getOrCreateSubjectFor(itemClass);
        subject.onNext(Result.response(Response.success(item)));
        subject.onCompleted();
    }

    @NonNull
    private ReplaySubject<Result<T>> getOrCreateSubjectFor(Class<T> itemClass) {
        if (subjects.get(itemClass) != null) {
            return subjects.get(itemClass);
        }
        ReplaySubject<Result<T>> subject = ReplaySubject.create();
        subjects.put(itemClass, subject);
        return subject;
    }
}
