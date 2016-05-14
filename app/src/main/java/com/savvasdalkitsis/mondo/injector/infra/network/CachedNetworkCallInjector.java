package com.savvasdalkitsis.mondo.injector.infra.network;

import com.savvasdalkitsis.mondo.infra.network.CachedNetworkCall;
import com.savvasdalkitsis.mondo.infra.network.NoOnErrorCachedNetworkCall;

import static com.savvasdalkitsis.mondo.injector.infra.cache.ObservableCacheInjector.observableCache;

public class CachedNetworkCallInjector {
    public static <F, T> CachedNetworkCall<F, T> cachedNetworkCall() {
        return new NoOnErrorCachedNetworkCall<>(observableCache());
    }
}
