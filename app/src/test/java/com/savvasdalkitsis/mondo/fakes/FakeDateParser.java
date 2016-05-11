package com.savvasdalkitsis.mondo.fakes;

import com.savvasdalkitsis.mondo.infra.date.DateParser;

import java.util.Date;
import java.util.HashMap;

public class FakeDateParser implements DateParser {

    private final HashMap<String, Date> formats = new HashMap<>();

    @Override
    public Date parse(String stringRepresentation) {
        return formats.get(stringRepresentation);
    }

    public void format(String stringFormat, Date date) {
        formats.put(stringFormat, date);
    }
}
