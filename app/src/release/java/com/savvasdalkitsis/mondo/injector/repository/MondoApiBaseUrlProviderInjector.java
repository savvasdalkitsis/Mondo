package com.savvasdalkitsis.mondo.injector.repository;

import com.savvasdalkitsis.mondo.repository.MondoApiBaseUrlProvider;
import com.savvasdalkitsis.mondo.repository.ProductionMondoApiBaseUrlProvider;

public class MondoApiBaseUrlProviderInjector {

    public static MondoApiBaseUrlProvider mondoApiBaseUrlProvider() {
        return new ProductionMondoApiBaseUrlProvider();
    }
}
