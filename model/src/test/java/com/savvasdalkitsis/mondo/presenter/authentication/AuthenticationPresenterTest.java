package com.savvasdalkitsis.mondo.presenter.authentication;

import com.savvasdalkitsis.mondo.fakes.FakeAuthenticationUseCase;
import com.savvasdalkitsis.mondo.model.authentication.AuthenticationData;
import com.savvasdalkitsis.mondo.view.authentication.AuthenticationView;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class AuthenticationPresenterTest {

    @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();
    @Mock private AuthenticationView view;

    @Test
    public void attemptsAuthenticationWhenStarted() {
        FakeAuthenticationUseCase authenticationUseCase = new FakeAuthenticationUseCase();
        AuthenticationPresenter presenter = new AuthenticationPresenter(authenticationUseCase);
        AuthenticationData authenticationData = AuthenticationData.builder().code("code").build();

        authenticationUseCase.accepts(authenticationData);

        mockery.checking(new Expectations() {{
            oneOf(view).displayLoading();
        }});

        presenter.startPresenting(view, authenticationData);

        mockery.checking(new Expectations() {{
            oneOf(view).successfulAuthentication();
        }});

        authenticationUseCase.emitSuccessFor(authenticationData);
    }

}