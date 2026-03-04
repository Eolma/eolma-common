package com.eolma.common.logging;

import org.slf4j.MDC;

// MDC 기반 traceId, 서비스명 관리
public final class LogContext {

    public static final String TRACE_ID = "traceId";
    public static final String SERVICE_NAME = "serviceName";

    private LogContext() {
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void setServiceName(String serviceName) {
        MDC.put(SERVICE_NAME, serviceName);
    }

    public static void clear() {
        MDC.clear();
    }
}
