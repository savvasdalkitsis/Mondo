package com.savvasdalkitsis.mondo.usecase.authentication;

import com.savvasdalkitsis.mondo.fakes.FakeCredentialsRepository;
import com.savvasdalkitsis.mondo.fakes.FakeMondoApi;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.repository.model.ApiOAuthToken;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MondoAuthenticationUseCaseTest {

    private static final String REFRESH_TOKEN = "refresh_token";
    @Rule
    public TestRule android = new AndroidRxSchedulerRuleImmediate();
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";
    private static final AuthenticationData AUTHENTICATION_DATA = AuthenticationData.builder().code(CODE).build();
    private static final String ACCESS_TOKEN = "access_token";
    private final FakeMondoApi mondoApi = new FakeMondoApi();
    private final FakeCredentialsRepository credentialsRepository = new FakeCredentialsRepository();
    private final MondoAuthenticationUseCase useCase = new MondoAuthenticationUseCase(mondoApi,
            credentialsRepository, CLIENT_ID, CLIENT_SECRET);

    @Test
    public void persistsAuthenticationTokenWhenSuccessfullyExchangedAuthCode() {
        mondoApi.acceptsOAuthCall(CLIENT_ID, CLIENT_SECRET, CODE);

        useCase.authenticate(AUTHENTICATION_DATA).subscribe();

        mondoApi.emitSuccessfulOAuthFor(CLIENT_ID, CLIENT_SECRET, CODE, ApiOAuthToken.builder().accessToken(ACCESS_TOKEN).build());

        assertThat(credentialsRepository.getAccessToken(), equalTo(ACCESS_TOKEN));
    }

    @Test
    public void persistsRefreshTokenWhenSuccessfullyExchangedAuthCode() {
        mondoApi.acceptsOAuthCall(CLIENT_ID, CLIENT_SECRET, CODE);

        useCase.authenticate(AUTHENTICATION_DATA).subscribe();

        mondoApi.emitSuccessfulOAuthFor(CLIENT_ID, CLIENT_SECRET, CODE, ApiOAuthToken.builder().refreshToken(REFRESH_TOKEN).build());

        assertThat(credentialsRepository.getRefreshToken(), equalTo(REFRESH_TOKEN));
    }

}