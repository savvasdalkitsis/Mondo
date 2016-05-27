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
import com.savvasdalkitsis.mondo.model.Response;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTransformers {

    public static <T> Observable.Transformer<T, T> applyAndroidSchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<Response<T>, Response<T>> onErrorToErrorResponse() {
        return observable -> observable
                .onErrorResumeNext(error -> {
                    Logger.error("RxTransformers", "onError()", error);
                    return Observable.just(Response.<T>error());
                });
    }
}
