package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;

public class MondoArrangements {

    private MatchingDispatcher dispatcher;

    public MondoArrangements(MatchingDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void isDown() {
        dispatcher.clearAll();
    }
}
