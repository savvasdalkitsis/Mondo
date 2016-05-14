package com.savvasdalkitsis.mondo.infra.cache;

import com.google.gson.GsonBuilder;
import com.savvasdalkitsis.mondo.fakes.FakeMondoPreferences;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.Subscription;
import rx.subjects.PublishSubject;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class PreferencesObservableCacheTest {

    private final PreferencesObservableCache<String> cache = new PreferencesObservableCache<>(new FakeMondoPreferences(), new GsonBuilder().create());
    private final PublishSubject<Result<String>> subject = PublishSubject.create();
    private final PublishSubject<Result<String>> subject2 = PublishSubject.create();
    private final HamcrestTestSubscriber<Result<String>> subscriber = new HamcrestTestSubscriber<>();
    private final HamcrestTestSubscriber<Result<String>> subscriber2 = new HamcrestTestSubscriber<>();
    private final Result<String> successfulItem = Result.response(Response.success("item"));

    @Test
    public void emitsItemsFromWrappedObservable() {
        subscribe(subscriber, subject);

        subject.onNext(successfulItem);
        subject.onCompleted();

        subscriber.assertFinishedWithItems(sameBeanAs(successfulItem));
    }

    @Test
    public void cachesAndEmitsSuccessfulItems() {
        subscribe(subscriber, subject);

        subject.onNext(successfulItem);
        subject.onCompleted();

        subscribe(subscriber2, subject2);

        subject2.onError(new IOException());

        subscriber2.awaitTerminalEvent();
        subscriber2.assertReceivedItems(sameBeanAs(successfulItem));
        subscriber2.assertError(IOException.class);
    }

    private Subscription subscribe(HamcrestTestSubscriber<Result<String>> subscriber, PublishSubject<Result<String>> subject) {
        return cache.cache(subject, String.class).subscribe(subscriber);
    }

}