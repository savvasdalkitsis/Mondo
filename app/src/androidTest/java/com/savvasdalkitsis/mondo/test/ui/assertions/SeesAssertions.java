/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
