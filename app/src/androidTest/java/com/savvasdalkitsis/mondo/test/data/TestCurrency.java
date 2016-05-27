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
package com.savvasdalkitsis.mondo.test.data;

public enum TestCurrency {
    GBP("GBP", "£");

    private final String apiName;
    private final String displayString;

    TestCurrency(String apiName, String displayString) {
        this.apiName = apiName;
        this.displayString = displayString;
    }

    public String apiName() {
        return apiName;
    }

    public String displayString() {
        return displayString;
    }
}
