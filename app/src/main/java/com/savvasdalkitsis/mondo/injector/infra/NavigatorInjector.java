package com.savvasdalkitsis.mondo.injector.infra;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.MainNavigator;
import com.savvasdalkitsis.mondo.infra.MondoAuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.MondoMainNavigator;
import com.savvasdalkitsis.mondo.injector.ApplicationInjector;

public class NavigatorInjector {

    public static AuthenticationNavigator authenticationRepository() {
        return new MondoAuthenticationNavigator(ApplicationInjector.getApplication());
    }

    public static MainNavigator mainRepository() {
        return new MondoMainNavigator(ApplicationInjector.getApplication());
    }
}
