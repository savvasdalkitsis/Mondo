package com.savvasdalkitsis.mondo;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.savvasdalkitsis.mondo.test.server.MatchingDispatcher;
import com.savvasdalkitsis.mondo.test.ui.actors.User;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsActivity;

import org.junit.Before;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class MondoTest extends ActivityInstrumentationTestCase2<TransactionsActivity> {

    private MockWebServer server;
    private MatchingDispatcher dispatcher;

    public MondoTest() {
        super(TransactionsActivity.class);
    }

    protected User user;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        server = new MockWebServer();
        dispatcher = new MatchingDispatcher();
        server.setDispatcher(dispatcher);
        user = new User(this, dispatcher);
        server.start();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        server.shutdown();
    }

    public void startApp() {
        getActivity();
    }

}