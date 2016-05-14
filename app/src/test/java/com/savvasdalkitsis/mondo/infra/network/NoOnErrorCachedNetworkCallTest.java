package com.savvasdalkitsis.mondo.infra.network;

import com.savvasdalkitsis.mondo.fakes.NoOpObservableCache;
import com.savvasdalkitsis.mondo.model.Response;
import com.savvasdalkitsis.mondo.rx.AndroidRxSchedulerRuleImmediate;
import com.savvasdalkitsis.mondo.subscribers.HamcrestTestSubscriber;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.Subscription;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static retrofit2.Response.success;

public class NoOnErrorCachedNetworkCallTest {

    @Rule public TestRule timeout = Timeout.seconds(2);
    @Rule public TestRule android = new AndroidRxSchedulerRuleImmediate();
    private HamcrestTestSubscriber<Response<String>> subscriber = new HamcrestTestSubscriber<>();
    private final NoOnErrorCachedNetworkCall<Integer, String> call = new NoOnErrorCachedNetworkCall<>(new NoOpObservableCache<>());

    @Test
    public void mapsResponsesWithMapper() {
        subscribeTo(Observable.just(Result.response(success(1))));

        subscriber.assertFinishedWithItems(sameBeanAs(Response.success("1")));
    }

    @Test
    public void respondsWithResponseErrorForErrors() {
        subscribeTo(Observable.<Result<Integer>>error(new IOException()));

        subscriber.assertFinishedWithItems(sameBeanAs(Response.error()));
    }

    @Test
    public void respondsWithResponseErrorWhenResultHasNon200Response() {
        subscribeTo(Observable.<Result<Integer>>just(Result.response(retrofit2.Response.error(500, ResponseBody.create(null, "1")))));

        subscriber.assertFinishedWithItems(sameBeanAs(Response.error()));
    }

    @Test
    public void respondsWithResponseErrorWhenResultHasError() {
        subscribeTo(Observable.<Result<Integer>>just(Result.error(new IOException())));

        subscriber.assertReceivedItems(sameBeanAs(Response.error()));
    }

    @Test
    public void isExecutedAsynchronously() {
        Semaphore semaphore = new Semaphore(0);
        subscribeTo(Observable.<Result<Integer>>create(
                subscriber -> {
                    semaphore.acquireUninterruptibly();
                }));

        semaphore.release();
    }

    private Subscription subscribeTo(Observable<Result<Integer>> observable) {
        return observable
                .compose(call.withMapper(Object::toString, Integer.class))
                .subscribe(subscriber);
    }
}