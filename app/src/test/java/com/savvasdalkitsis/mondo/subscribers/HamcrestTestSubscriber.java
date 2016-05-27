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
package com.savvasdalkitsis.mondo.subscribers;

import org.hamcrest.Matcher;

import rx.observers.TestSubscriber;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class HamcrestTestSubscriber<T> extends TestSubscriber<T> {

    @SafeVarargs
    public final void assertFinishedWithItems(Matcher<T>... itemMatchers) {
        awaitTerminalEvent();
        assertCompleted();
        assertReceivedItems(itemMatchers);
    }

    @SafeVarargs
    public final void assertReceivedItems(Matcher<T>... itemMatchers) {
        assertThat(getOnNextEvents(), contains(itemMatchers));
    }
}
