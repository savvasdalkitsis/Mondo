package com.savvasdalkitsis.mondo.repository;

import com.savvasdalkitsis.mondo.BuildConfig;

public class ProductionMondoApiBaseUrlProvider implements MondoApiBaseUrlProvider {

    @Override
    public String getBaseUrl() {
        return BuildConfig.MONDO_API_BASE_URL;
    }
}
