package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.infra.AuthenticationNavigator;
import com.savvasdalkitsis.mondo.infra.ConfigurableAuthenticationNavigator;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ConfigurableAuthenticationNavigatorTest {

    @Rule public JUnitRuleMockery mockery = new JUnitRuleMockery();
    @Mock private AuthenticationNavigator real;
    @Mock private AuthenticationNavigator fake;
    private ConfigurableAuthenticationNavigator navigator;

    @Before
    public void setUp() throws Exception {
        navigator = new ConfigurableAuthenticationNavigator(real, fake);
    }

    @Test
    public void usesRealNavigatorByDefault() {
        mockery.checking(new Expectations() {{
            oneOf(real).navigateToAuthentication();
        }});

        navigator.navigateToAuthentication();
    }

    @Test
    public void usesFakeNavigatorWhenInstructed() {
        navigator.setUseFake(true);

        mockery.checking(new Expectations() {{
            oneOf(fake).navigateToAuthentication();
        }});

        navigator.navigateToAuthentication();
    }

}