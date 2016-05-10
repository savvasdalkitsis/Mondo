package com.savvasdalkitsis.mondo.test.ui.assertions;

import android.support.annotation.IdRes;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class SeesAssertions {

    public static ViewInteraction seesText(double text) {
        return seesText(String.valueOf(text));
    }

    public static ViewInteraction seesText(String text) {
        return onView(withText(text)).check(matches(isDisplayed()));
    }

    public static ViewInteraction seesTextInsideViewWithId(@IdRes int id, double text) {
        return seesTextInsideViewWithId(id, String.valueOf(text));
    }

    public static ViewInteraction seesTextInsideViewWithId(@IdRes int id, String text) {
        return seesOnView(withId(id), hasDescendant(withText(text)));
    }

    public static ViewInteraction seesOnView(Matcher<View> viewMatcher, Matcher<View> assertionMatcher) {
        return onView(viewMatcher).check(matches(allOf(isDisplayed(), assertionMatcher)));
    }
}
