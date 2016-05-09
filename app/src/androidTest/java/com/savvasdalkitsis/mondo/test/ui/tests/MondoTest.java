package com.savvasdalkitsis.mondo.test.ui.tests;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.savvasdalkitsis.mondo.repository.ConfigurableApiBaseUrlProvider;
import com.savvasdalkitsis.mondo.repository.CredentialsRepository;
import com.savvasdalkitsis.mondo.rx.RxIdlingResource;
import com.savvasdalkitsis.mondo.test.data.TestAccountId;
import com.savvasdalkitsis.mondo.test.ui.actors.Mondo;
import com.savvasdalkitsis.mondo.test.ui.actors.User;
import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;
import com.savvasdalkitsis.mondo.view.transactions.TransactionsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockWebServer;
import rx.plugins.RxJavaResettablePlugins;

import static com.savvasdalkitsis.mondo.injector.repository.CredentialsRepositoryInjector.credentialsRepository;
import static com.savvasdalkitsis.mondo.injector.repository.MondoApiBaseUrlProviderInjector.configurableMondoApiBaseUrlProvider;

@RunWith(AndroidJUnit4.class)
public class MondoTest extends ActivityInstrumentationTestCase2<TransactionsActivity> {

    private final ConfigurableApiBaseUrlProvider configurableApiBaseUrlProvider = configurableMondoApiBaseUrlProvider();
    private final CredentialsRepository credentialsRepository = credentialsRepository();
    private MockWebServer server;

    public MondoTest() {
        super(TransactionsActivity.class);
    }

    protected User user;
    protected Mondo mondo;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        RxJavaResettablePlugins.resetPlugins();
        RxJavaResettablePlugins.getInstance().registerObservableExecutionHook(RxIdlingResource.get());
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        server = new MockWebServer();
        MatchingDispatcher dispatcher =  new MatchingDispatcher();
        server.setDispatcher(dispatcher);
        user = new User(this, dispatcher, getInstrumentation().getContext());
        mondo = new Mondo(dispatcher);
        server.start();
        credentialsRepository.saveAccountId(TestAccountId.TEST_ACCOUNT_ID);
        configurableApiBaseUrlProvider.overrideUrl(server.url("").toString());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        server.shutdown();
        RxJavaResettablePlugins.resetPlugins();
    }

    public void startApp() {
        getActivity();
    }

}