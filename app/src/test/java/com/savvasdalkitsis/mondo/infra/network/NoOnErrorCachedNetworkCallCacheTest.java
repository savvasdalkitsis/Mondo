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

import com.savvasdalkitsis.mondo.fakes.FakeObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class NoOnErrorCachedNetworkCallCacheTest {

    @Rule public TestRule timeout = Timeout.seconds(2);
    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    private HamcrestTestSubscriber<Response<String>> subscriber = new HamcrestTestSubscriber<>();
    private final FakeObservableCache<Integer> observableCache = new FakeObservableCache<>();
    private final NoOnErrorCachedNetworkCall<Integer, String> call = new NoOnErrorCachedNetworkCall<>(observableCache);

    @Test
    public void usesCache() {
        Observable.<Result<Integer>>empty()
                .compose(call.withMapper(Object::toString, Integer.class))
                .subscribe(subscriber);

        observableCache.emitSuccessfulResultFor(Integer.class, 1);

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success("1")));
    }
}