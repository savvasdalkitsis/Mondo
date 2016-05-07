package com.savvasdalkitsis.mondo.injector.repository;

import com.savvasdalkitsis.mondo.BuildConfig;
import com.savvasdalkitsis.mondo.repository.ConfigurableApiBaseUrlProvider;
import com.savvasdalkitsis.mondo.repository.MondoApiBaseUrlProvider;

public class MondoApiBaseUrlProviderInjector {

    private static final ConfigurableApiBaseUrlProvider provider = new ConfigurableApiBaseUrlProvider(BuildConfig.MONDO_API_BASE_URL);

    public static MondoApiBaseUrlProvider mondoApiBaseUrlProvider() {
        return provider;
    }

    public static ConfigurableApiBaseUrlProvider configurableMondoApiBaseUrlProvider() {
        return provider;
    }
}
