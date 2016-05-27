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
package com.savvasdalkitsis.mondo.infra.date;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MondoDateParser implements DateParser {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ", Locale.getDefault());

    @Override
    public Date parse(String stringRepresentation) {
        try {
            return dateFormat.parse(stringRepresentation.replace('T', ' ').replace('Z', ' '));
        } catch (ParseException e) {
            Log.e("DATE", "Could not parse date " + stringRepresentation, e);
        }
        return new Date(0);
    }
}
