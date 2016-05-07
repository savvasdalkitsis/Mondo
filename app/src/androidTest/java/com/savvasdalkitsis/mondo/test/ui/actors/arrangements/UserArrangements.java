package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import com.savvasdalkitsis.mondo.test.data.TestCurrency;
import com.savvasdalkitsis.mondo.test.data.TestResponses;
import com.savvasdalkitsis.mondo.test.server.MatchingDispatcher;

import org.hamcrest.Matcher;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static com.savvasdalkitsis.mondo.test.matchers.RecordedRequestMatchers.withPath;

public class UserArrangements {

    private MatchingDispatcher dispatcher;

    public UserArrangements(MatchingDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void hasBalance(double balance, TestCurrency currency) {
        respond(withPath("/balance"), TestResponses.balance(balance, currency));
    }

    public void hasSpentToday(double amount, TestCurrency currency) {
        respond(withPath("/balance"), TestResponses.spentToday(amount, currency));
    }

    private void respond(Matcher<RecordedRequest> requestMatcher, String body) {
        dispatcher.matchRequest(requestMatcher, new MockResponse()
                .setResponseCode(200)
                .setBody(body));
    }
}
