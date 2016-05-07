package com.savvasdalkitsis.mondo.repository;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ConfigurableApiBaseUrlProviderTest {

    private static final String URL = "url";
    private final ConfigurableApiBaseUrlProvider provider = new ConfigurableApiBaseUrlProvider(URL);

    @Test
    public void returnsInjectedUrlIfNotConfigured() {
        assertProvidedUrlIs(URL);
    }

    private void assertProvidedUrlIs(String url) {
        assertThat(provider.getBaseUrl(), equalTo(url));
    }

    @Test
    public void returnsOverriddenUrlWhenConfigured() {
        provider.overrideUrl("overridden_url");

        assertProvidedUrlIs("overridden_url");
    }

}