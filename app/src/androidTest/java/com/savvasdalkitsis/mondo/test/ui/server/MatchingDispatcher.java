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
        return new MockResponse().setResponseCode(404);
    }

    public void matchRequest(Matcher<RecordedRequest> requestMatcher, MockResponse response) {
        responses.put(requestMatcher, response);
    }
}
