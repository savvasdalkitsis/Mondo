package com.savvasdalkitsis.mondo.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

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

    public static Matcher<RecordedRequest> anywhere() {
        return new TypeSafeDiagnosingMatcher<RecordedRequest>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Matches all requests");
            }

            @Override
            protected boolean matchesSafely(RecordedRequest item, Description mismatchDescription) {
                return true;
            }
        };
    }
}
