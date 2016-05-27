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