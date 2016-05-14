package com.savvasdalkitsis.mondo.subscribers;

import org.hamcrest.Matcher;

import rx.observers.TestSubscriber;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class HamcrestTestSubscriber<T> extends TestSubscriber<T> {

    @SafeVarargs
    public final void assertFinishedWithItems(Matcher<T>... itemMatchers) {
        awaitTerminalEvent();
        assertCompleted();
        assertReceivedItems(itemMatchers);
    }

    @SafeVarargs
    public final void assertReceivedItems(Matcher<T>... itemMatchers) {
        assertThat(getOnNextEvents(), contains(itemMatchers));
    }
}
