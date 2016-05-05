package com.savvasdalkitsis.mondo.test.assertions;

import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SeesAssertions {

    public static ViewInteraction seesText(String text) {
        return onView(withText(text)).check(matches(isDisplayed()));
    }
}
