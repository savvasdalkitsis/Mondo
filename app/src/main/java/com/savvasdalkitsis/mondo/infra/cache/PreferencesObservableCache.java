package com.savvasdalkitsis.mondo.infra.cache;

import com.google.gson.Gson;
import com.savvasdalkitsis.mondo.infra.log.Logger;
import com.savvasdalkitsis.mondo.infra.preferences.MondoPreferences;
import com.savvasdalkitsis.mondo.infra.preferences.PreferenceKeys;
import com.savvasdalkitsis.mondo.util.StringUtils;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;

import static retrofit2.Response.success;
import static retrofit2.adapter.rxjava.Result.response;
import static rx.Observable.concat;
import static rx.Observable.defer;
import static rx.Observable.empty;
import static rx.Observable.just;

public class PreferencesObservableCache<T> implements ObservableCache<T> {

    private final Gson gson;
    private final MondoPreferences mondoPreferences;

    public PreferencesObservableCache(MondoPreferences mondoPreferences, Gson gson) {
        this.gson = gson;
        this.mondoPreferences = mondoPreferences;
    }

    @Override
    public Observable<Result<T>> cache(Observable<Result<T>> observable, Class<T> itemClass) {
        return concat(observable(itemClass), observable).doOnNext(item -> save(item, itemClass));
    }

    private Observable<Result<T>> observable(Class<T> itemClass) {
        return defer(() -> {
            String item = mondoPreferences.getStringPreference(getPreferenceKey(itemClass));
            if (StringUtils.isNotEmptyNorNull(item)) {
                try {
                    T t = gson.fromJson(item, itemClass);
                    return just(response(success(t)));
                } catch (Exception e) {
                    Logger.error("ObservableCache", "Error reading cached item", e);
                }
            }
            return empty();
        });
    }

    private void save(Result<T> result, Class<T> itemClass) {
        if (!result.isError() && result.response().isSuccessful()) {
            mondoPreferences.putStringPreference(getPreferenceKey(itemClass), gson.toJson(result.response().body()));
        }
    }

    private String getPreferenceKey(Class<T> itemClass) {
        return String.format(PreferenceKeys.KEY_OBSERVABLE_CACHE, itemClass.getName());
    }
}
