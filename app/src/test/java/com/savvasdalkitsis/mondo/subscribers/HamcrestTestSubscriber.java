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
        assertThat(getOnNextEvents(), contains(itemMatchers));
    }
}
