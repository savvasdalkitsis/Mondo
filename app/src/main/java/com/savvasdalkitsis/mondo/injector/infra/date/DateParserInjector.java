package com.savvasdalkitsis.mondo.injector.infra.date;

import com.savvasdalkitsis.mondo.infra.date.DateParser;
import com.savvasdalkitsis.mondo.infra.date.MondoDateParser;

public class DateParserInjector {

    public static DateParser dateParser() {
        return new MondoDateParser();
    }
}
