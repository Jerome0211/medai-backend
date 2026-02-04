package com.medai.util;

import java.util.UUID;

public class TraceIdUtil {
    public static String newTraceId() {
        return UUID.randomUUID().toString();
    }
}
