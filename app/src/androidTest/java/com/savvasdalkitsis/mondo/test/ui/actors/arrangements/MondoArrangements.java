package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

public class MondoArrangements {

    private MockWebServer server;

    public MondoArrangements(MockWebServer server) {
        this.server = server;
    }

    public void cannotBeContacted() {
        try {
            server.shutdown();
        } catch (IOException e) {
            throw new RuntimeException("Could not stop mock server", e);
        }
    }
}
