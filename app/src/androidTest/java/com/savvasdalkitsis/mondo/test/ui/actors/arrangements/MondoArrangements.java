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
package com.savvasdalkitsis.mondo.test.ui.actors.arrangements;

import com.savvasdalkitsis.mondo.test.ui.server.MatchingDispatcher;

public class MondoArrangements {

    private MatchingDispatcher dispatcher;

    public MondoArrangements(MatchingDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void isDown() {
        dispatcher.clearAll();
    }
}
