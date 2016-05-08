package com.savvasdalkitsis.mondo.test.ui.actors.actions;

import com.savvasdalkitsis.mondo.test.ui.MondoTest;

public class UserActions {

    private MondoTest mondoTest;

    public UserActions(MondoTest mondoTest) {
        this.mondoTest = mondoTest;
    }

    public void launchesMondo() {
        mondoTest.startApp();
    }
}
