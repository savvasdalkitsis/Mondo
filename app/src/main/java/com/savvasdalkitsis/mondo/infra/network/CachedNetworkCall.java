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

import com.savvasdalkitsis.mondo.model.Response;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

public interface CachedNetworkCall<F, T> {
    Observable.Transformer<Result<F>, Response<T>> withMapper(Func1<F, T> mapper, Class<F> itemClass);
}
