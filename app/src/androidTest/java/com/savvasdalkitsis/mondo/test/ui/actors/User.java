package com.savvasdalkitsis.mondo.test.ui.actors;

import com.savvasdalkitsis.mondo.MondoTest;
import com.savvasdalkitsis.mondo.test.server.MatchingDispatcher;
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

    public User(MondoTest mondoTest, MatchingDispatcher dispatcher) {
        userActions = new UserActions(mondoTest);
        userArrangements = new UserArrangements(dispatcher);
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
