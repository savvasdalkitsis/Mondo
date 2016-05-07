package com.savvasdalkitsis.mondo.repository;

public class ConfigurableApiBaseUrlProvider implements MondoApiBaseUrlProvider {

    private String url;
    private String overriddenUrl;

    public ConfigurableApiBaseUrlProvider(String url) {
        this.url = url;
    }

    @Override
    public String getBaseUrl() {
        return overriddenUrl != null ? overriddenUrl : url;
    }

    public void overrideUrl(String overriddenUrl) {
        this.overriddenUrl = overriddenUrl;
    }
}
