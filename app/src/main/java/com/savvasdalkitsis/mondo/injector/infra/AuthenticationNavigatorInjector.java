package com.savvasdalkitsis.mondo.injector.infra;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.MondoAuthenticationNavigator;
import com.savvasdalkitsis.mondo.injector.ApplicationInjector;

public class AuthenticationNavigatorInjector {
    public static AuthenticationNavigator authenticationRepository() {
        return new MondoAuthenticationNavigator(ApplicationInjector.getApplication());
    }
}
