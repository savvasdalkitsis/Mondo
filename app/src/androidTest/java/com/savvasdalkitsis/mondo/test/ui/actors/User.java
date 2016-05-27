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
package com.savvasdalkitsis.mondo.test.ui.actors;

import android.content.Context;

import com.savvasdalkitsis.mondo.test.ui.tests.MondoTest;
import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;
import com.savvasdalkitsis.mondo.test.ui.actors.actions.UserActions;
import com.savvasdalkitsis.mondo.test.ui.actors.arrangements.UserArrangements;
import com.savvasdalkitsis.mondo.test.ui.actors.assertions.UserAssertions;
import com.shazam.gwen.gwt.Given;
import com.shazam.gwen.gwt.Then;
import com.shazam.gwen.gwt.When;

public class User implements Given<UserArrangements>, When<UserActions>, Then<UserAssertions> {

    private final UserArrangements userArrangements;
    private final UserActions userActions;
    private final UserAssertions userAssertions = new UserAssertions();

    public User(MondoTest mondoTest, MatchingDispatcher dispatcher, Context instrumentationContext) {
        userActions = new UserActions(mondoTest);
        userArrangements = new UserArrangements(dispatcher, instrumentationContext);
    }

    @Override
    public UserArrangements given() {
        return userArrangements;
    }

    @Override
    public UserActions when() {
        return userActions;
    }

    @Override
    public UserAssertions then() {
        return userAssertions;
    }
}
