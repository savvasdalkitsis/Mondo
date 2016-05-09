package com.savvasdalkitsis.mondo.presenter.authentication;

import com.savvasdalkitsis.mondo.fakes.FakeAuthenticationUseCase;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.view.authentication.AuthenticationView;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AuthenticationPresenterTest {

    @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();
    @Mock private AuthenticationView view;
    private final FakeAuthenticationUseCase authenticationUseCase = new FakeAuthenticationUseCase();
    private final AuthenticationPresenter presenter = new AuthenticationPresenter(authenticationUseCase);
    private final AuthenticationData authenticationData = AuthenticationData.builder().code("code").build();

    @Before
    public void setUp() throws Exception {
        authenticationUseCase.accepts(authenticationData);
    }

    @Test
    public void attemptsAuthenticationWhenStarted() {
        mockery.checking(new Expectations() {{
            oneOf(view).displayLoading();
        }});

        presenter.startPresenting(view, authenticationData);

        mockery.checking(new Expectations() {{
            oneOf(view).successfulAuthentication();
        }});

        authenticationUseCase.emitSuccessfulResponseFor(authenticationData);
    }

    @Test
    public void notifiesViewOfErrorAuthenticating() {
        ignoreLoading();
        presenter.startPresenting(view, authenticationData);

        mockery.checking(new Expectations() {{
            oneOf(view).errorAuthenticating();
        }});

        authenticationUseCase.emitErrorResponseFor(authenticationData);
    }

    @Test
    public void doesNotNotifyViewOfAnythingAfterStopping() {
        ignoreLoading();
        presenter.startPresenting(view, authenticationData);

        presenter.stopPresenting();

        mockery.checking(new Expectations() {{
            never(view);
        }});

        authenticationUseCase.emitSuccessfulResponseFor(authenticationData);
    }

    private void ignoreLoading() {
        mockery.checking(new Expectations() {{
            ignoring(view).displayLoading();
        }});
    }

}