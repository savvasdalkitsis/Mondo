package com.savvasdalkitsis.mondo.rx;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTransformers {

    private static final String TAG = "ObservableLog";

    public static  <T> Observable.Transformer<T, T> androidNetworkCall() {
        return observable -> observable
                .doOnError(error -> Log.e(TAG, "onError()", error))
                .doOnNext(item -> Log.v(TAG, "onNext() -> " + item.toString()))
                .doOnSubscribe(() -> Log.v(TAG, "onSubscribe()"))
                .doOnTerminate(() -> Log.v(TAG, "onTerminate()"))
                .doOnUnsubscribe(() -> Log.v(TAG, "onUnsubscribe()"))
                .doOnCompleted(() -> Log.v(TAG, "onCompleted()"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
