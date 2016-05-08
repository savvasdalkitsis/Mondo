package com.savvasdalkitsis.mondo.injector.infra;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.ConfigurableAuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.FakeAuthenticationRepository;
import com.savvasdalkitsis.mondo.infra.MondoAuthenticationNavigator;

import static com.savvasdalkitsis.mondo.injector.ApplicationInjector.getApplication;

public class AuthenticationNavigatorInjector {

    private static final ConfigurableAuthenticationNavigator navigator =
            new ConfigurableAuthenticationNavigator(new MondoAuthenticationNavigator(getApplication()), 
                    fakeAuthenticationRepository());

    public static AuthenticationNavigator authenticationRepository() {
        return navigator;
    }

    public static AuthenticationNavigator fakeAuthenticationRepository() {
        return new FakeAuthenticationRepository(getApplication());
    }

    public static ConfigurableAuthenticationNavigator configurableAuthenticationNavigator() {
        return navigator;
    }
}
