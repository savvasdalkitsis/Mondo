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
