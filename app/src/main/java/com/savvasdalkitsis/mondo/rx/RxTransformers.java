package com.savvasdalkitsis.mondo.rx;

import com.savvasdalkitsis.mondo.infra.log.Logger;
import com.savvasdalkitsis.mondo.model.Response;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTransformers {

    public static <T> Observable.Transformer<T, T> applyAndroidSchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable.Transformer<Response<T>, Response<T>> onErrorToErrorResponse() {
        return observable -> observable
                .onErrorResumeNext(error -> {
                    Logger.error("RxTransformers", "onError()", error);
                    return Observable.just(Response.<T>error());
                });
    }
}
