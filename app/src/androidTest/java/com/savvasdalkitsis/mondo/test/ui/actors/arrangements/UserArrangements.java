package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import com.savvasdalkitsis.mondo.test.data.TestCurrency;
import com.savvasdalkitsis.mondo.test.data.TestResponses;
import com.savvasdalkitsis.mondo.test.server.MatchingDispatcher;

import okhttp3.mockwebserver.MockResponse;

import static com.savvasdalkitsis.mondo.test.matchers.RecordedRequestMatchers.withPath;

public class UserArrangements {

    private MatchingDispatcher dispatcher;

    public UserArrangements(MatchingDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void hasBalance(double balance, TestCurrency currency) {
        dispatcher.matchRequest(withPath("/balance"), new MockResponse()
                .setResponseCode(200)
                .setBody(TestResponses.balance(balance, currency)));
    }
}
