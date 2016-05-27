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
package com.savvasdalkitsis.mondo.test.ui.server;

import android.util.Log;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.IdentityHashMap;
import java.util.Map;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public class MatchingDispatcher extends Dispatcher {

    private final Map<Matcher<RecordedRequest>, MockResponse> responses = new IdentityHashMap<>();

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        for (Map.Entry<Matcher<RecordedRequest>, MockResponse> response : responses.entrySet()) {
            Matcher<RecordedRequest> requestMatcher = response.getKey();
            if (requestMatcher.matches(request)) {
                return response.getValue();
            } else {
                StringDescription description = new StringDescription();
                description.appendText("Did not match request");
                requestMatcher.describeMismatch(request, description);
                Log.v(MatchingDispatcher.class.getName(), description.toString());
            }
        }
        return new MockResponse().setResponseCode(500);
    }

    public void matchRequest(Matcher<RecordedRequest> requestMatcher, MockResponse response) {
        responses.put(requestMatcher, response);
    }

    public void clearAll() {
        responses.clear();
    }
}
