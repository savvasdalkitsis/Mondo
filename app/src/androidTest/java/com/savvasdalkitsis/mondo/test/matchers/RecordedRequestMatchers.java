package com.savvasdalkitsis.mondo.test.matchers;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import okhttp3.mockwebserver.RecordedRequest;

import static org.hamcrest.Matchers.equalTo;

public class RecordedRequestMatchers {

    public static Matcher<RecordedRequest> withPath(String path) {

        return new FeatureMatcher<RecordedRequest, String>(equalTo(path), "request path", "path") {
            @Override
            protected String featureValueOf(RecordedRequest recordedRequest) {
                return recordedRequest.getPath();
            }
        };
    }
}
