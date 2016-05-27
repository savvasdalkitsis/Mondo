/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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