package com.savvasdalkitsis.mondo.test.ui.tests;

import org.junit.Test;

import static com.shazam.gwen.Gwen.given;
import static com.shazam.gwen.Gwen.then;
import static com.shazam.gwen.Gwen.when;

public class UnauthenticatedUserTest extends MondoTest {

    @Test
    public void isRedirectedToAuthenticateWhenLaunchingTheApp() {
        given(user).isNotAuthenticated();

        when(user).launchesMondo();

        then(user).seesAuthenticationScreen();
    }
}
