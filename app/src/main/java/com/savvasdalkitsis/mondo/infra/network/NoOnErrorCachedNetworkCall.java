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
package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.model.Response;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.rx.RxTransformers.applyAndroidSchedulers;
import static com.savvasdalkitsis.mondo.rx.RxTransformers.onErrorToErrorResponse;

public class NoOnErrorCachedNetworkCall<F, T> implements CachedNetworkCall<F, T> {

    private ObservableCache<F> observableCache;

    public NoOnErrorCachedNetworkCall(ObservableCache<F> observableCache) {
        this.observableCache = observableCache;
    }

    @Override
    public Observable.Transformer<Result<F>, Response<T>> withMapper(Func1<F, T> mapper, Class<F> itemClass) {
        return observable -> observable
                .compose(observableCache.on(itemClass))
                .map(result -> {
                    if (!result.isError() && result.response().isSuccessful()) {
                        return Response.success(mapper.call(result.response().body()));
                    }
                    return Response.<T>error();
                })
                .compose(applyAndroidSchedulers())
                .compose(onErrorToErrorResponse());
    }
}
