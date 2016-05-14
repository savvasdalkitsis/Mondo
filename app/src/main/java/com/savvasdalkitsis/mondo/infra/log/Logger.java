package com.savvasdalkitsis.mondo.infra.log;

import java.util.logging.Level;

public class Logger {

    public static void error(String tag, String msg, Throwable error) {
        java.util.logging.Logger.getLogger(tag).log(Level.WARNING, msg, error);
    }
}
