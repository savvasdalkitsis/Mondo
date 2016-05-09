package com.savvasdalkitsis.mondo.usecase.authentication;

import com.savvasdalkitsis.mondo.fakes.FakeCredentialsRepository;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MondoAuthenticationUseCaseTest {

    @Rule
    public TestRule android = new AndroidRxSchedulerRuleImmediate();
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";
    private static final String AUTH_TOKEN = "auth_token";

    @Test
    public void persistsAuthenticationTokenWhenSuccessfullyExchangedAuthCode() {
        FakeMondoApi mondoApi = new FakeMondoApi();
        FakeCredentialsRepository credentialsRepository = new FakeCredentialsRepository();
        MondoAuthenticationUseCase useCase = new MondoAuthenticationUseCase(mondoApi,
                credentialsRepository, CLIENT_ID, CLIENT_SECRET);

        mondoApi.acceptsOAuthCall(CLIENT_ID, CLIENT_SECRET, CODE);

        useCase.authenticate(AuthenticationData.builder().code(CODE).build())
                .subscribe();

        mondoApi.emitSuccessfulOAuthFor(CLIENT_ID, CLIENT_SECRET, CODE, AUTH_TOKEN);

        assertThat(credentialsRepository.getAuthToken(), equalTo(AUTH_TOKEN));
    }

}