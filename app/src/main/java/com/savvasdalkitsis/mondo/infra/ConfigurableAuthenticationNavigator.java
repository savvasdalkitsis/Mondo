package com.savvasdalkitsis.mondo.infra;

public class ConfigurableAuthenticationNavigator implements AuthenticationNavigator {

    private boolean useFake = false;
    private AuthenticationNavigator authenticationNavigator;
    private AuthenticationNavigator fakeAuthenticationNavigator;

    public ConfigurableAuthenticationNavigator(AuthenticationNavigator authenticationNavigator,
                                               AuthenticationNavigator fakeAuthenticationNavigator) {
        this.authenticationNavigator = authenticationNavigator;
        this.fakeAuthenticationNavigator = fakeAuthenticationNavigator;
    }

    @Override
    public void navigateToAuthentication() {
        if (useFake) {
            fakeAuthenticationNavigator.navigateToAuthentication();
        } else {
            authenticationNavigator.navigateToAuthentication();
        }
    }

    public void setUseFake(boolean useFake) {
        this.useFake = useFake;
    }
}
