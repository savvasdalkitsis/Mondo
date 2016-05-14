package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.infra.cache.ObservableCache;
import com.savvasdalkitsis.mondo.model.Response;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Func1;

import static com.savvasdalkitsis.mondo.rx.RxTransformers.applyAndroidSchedulers;
import static com.savvasdalkitsis.mondo.rx.RxTransformers.onErrorToErrorResponse;

public class NoOnErrorCachedNetworkCall<F, T> implements CachedNetworkCall<F, T> {

    private ObservableCache<F> observableCache;

    public NoOnErrorCachedNetworkCall(ObservableCache<F> observableCache) {
        this.observableCache = observableCache;
    }

    @Override
    public Observable.Transformer<Result<F>, Response<T>> withMapper(Func1<F, T> mapper, Class<F> itemClass) {
        return observable -> observable
                .compose(observableCache.on(itemClass))
                .map(result -> {
                    if (!result.isError() && result.response().isSuccessful()) {
                        return Response.success(mapper.call(result.response().body()));
                    }
                    return Response.<T>error();
                })
                .compose(applyAndroidSchedulers())
                .compose(onErrorToErrorResponse());
    }
}
