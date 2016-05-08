package com.savvasdalkitsis.mondo.test.ui.actors;

import com.savvasdalkitsis.mondo.test.ui.actors.arrangements.MondoArrangements;
import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;
import com.shazam.gwen.gwt.Given;

public class Mondo implements Given<MondoArrangements> {

    private final MondoArrangements mondoArrangements;

    public Mondo(MatchingDispatcher dispatcher) {
        mondoArrangements = new MondoArrangements(dispatcher);
    }

    @Override
    public MondoArrangements given() {
        return mondoArrangements;
    }
}
