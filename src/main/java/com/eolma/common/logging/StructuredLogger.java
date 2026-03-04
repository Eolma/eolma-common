package com.eolma.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Structured Logging kv() 헬퍼
public final class StructuredLogger {

    private StructuredLogger() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    // key=value 형태의 로깅 파라미터 생성
    public static String kv(String key, Object value) {
        return key + "=" + value;
    }
}
