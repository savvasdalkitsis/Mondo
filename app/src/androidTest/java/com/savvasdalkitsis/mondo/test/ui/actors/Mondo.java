package com.savvasdalkitsis.mondo.test.ui.actors;

import com.savvasdalkitsis.mondo.test.ui.actors.arrangements.MondoArrangements;
import com.shazam.gwen.gwt.Given;

import okhttp3.mockwebserver.MockWebServer;

public class Mondo implements Given<MondoArrangements> {

    private final MondoArrangements mondoArrangements;

    public Mondo(MockWebServer server) {
        mondoArrangements = new MondoArrangements(server);
    }

    @Override
    public MondoArrangements given() {
        return mondoArrangements;
    }
}
