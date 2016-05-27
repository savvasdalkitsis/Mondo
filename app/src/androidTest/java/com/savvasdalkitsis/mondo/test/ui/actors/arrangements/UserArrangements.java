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
package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import android.content.Context;
import android.support.annotation.RawRes;

import com.savvasdalkitsis.mondo.test.data.TestAccountId;
import com.savvasdalkitsis.mondo.test.data.TestCurrency;
import com.savvasdalkitsis.mondo.test.data.TestResponses;
import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;

import org.hamcrest.Matcher;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static com.savvasdalkitsis.mondo.test.matchers.RecordedRequestMatchers.anywhere;
import static com.savvasdalkitsis.mondo.test.matchers.RecordedRequestMatchers.withPath;
import static com.savvasdalkitsis.mondo.test.util.RawUtils.fromRaw;

public class UserArrangements {

    private MatchingDispatcher dispatcher;
    private Context instrumentationContext;

    public UserArrangements(MatchingDispatcher dispatcher, Context instrumentationContext) {
        this.dispatcher = dispatcher;
        this.instrumentationContext = instrumentationContext;
    }

    public void hasBalance(int balance, TestCurrency currency) {
        respond(withPath("/balance?account_id="+ TestAccountId.TEST_ACCOUNT_ID),
                TestResponses.balance(balance, currency));
    }

    public void hasSpentToday(int amount, TestCurrency currency) {
        respond(withPath("/balance?account_id="+ TestAccountId.TEST_ACCOUNT_ID),
                TestResponses.spentToday(amount, currency));
    }

    public void hasTransactions(@RawRes int rawId) {
        respond(withPath("/transactions?expand[]=merchant&account_id="+ TestAccountId.TEST_ACCOUNT_ID),
                fromRaw(rawId, instrumentationContext.getResources()));
    }

    public void isNotAuthenticated() {
        respond(anywhere(), new MockResponse().setResponseCode(401));
    }

    private void respond(Matcher<RecordedRequest> requestMatcher, String body) {
        respond(requestMatcher, new MockResponse()
                .setResponseCode(200)
                .setBody(body));
    }

    private void respond(Matcher<RecordedRequest> requestMatcher, MockResponse response) {
        dispatcher.matchRequest(requestMatcher, response);
    }

}
