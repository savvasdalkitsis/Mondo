package com.savvasdalkitsis.mondo.util;

public class StringUtils {

    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmptyNorNull(String string) {
        return string != null && !string.isEmpty();
    }
}
