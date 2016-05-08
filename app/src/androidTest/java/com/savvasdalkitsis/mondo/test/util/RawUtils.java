package com.savvasdalkitsis.mondo.test.util;

import android.content.res.Resources;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.InputStream;

public class RawUtils {

    public static String fromRaw(@RawRes int rawId, Resources resources) {
        try {
            InputStream inputStream = resources.openRawResource(rawId);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            return new String(data);
        } catch (Exception e) {
            Log.e("Utils", "Could not read raw file", e);
        }
        return "";
    }
}
