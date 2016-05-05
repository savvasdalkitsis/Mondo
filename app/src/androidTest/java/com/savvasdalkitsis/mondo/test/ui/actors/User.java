package com.savvasdalkitsis.mondo.test.ui.actors;

import com.savvasdalkitsis.mondo.MondoTest;
import com.savvasdalkitsis.mondo.test.ui.actors.actions.UserActions;
import com.savvasdalkitsis.mondo.test.ui.actors.arrangements.UserArrangements;
import com.savvasdalkitsis.mondo.test.ui.actors.assertions.UserAssertions;
import com.shazam.gwen.gwt.Given;
import com.shazam.gwen.gwt.Then;
import com.shazam.gwen.gwt.When;

public class User implements Given<UserArrangements>, When<UserActions>, Then<UserAssertions> {

    private final UserArrangements userArrangements = new UserArrangements();
    private final UserActions userActions;
    private final UserAssertions userAssertions = new UserAssertions();

    public User(MondoTest mondoTest) {
        userActions = new UserActions(mondoTest);
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
