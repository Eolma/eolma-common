package com.eolma.common.idempotency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 이벤트 중복 처리 방지 유틸리티
public class IdempotencyChecker {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyChecker.class);
    private final ProcessedEventRepository repository;

    public IdempotencyChecker(ProcessedEventRepository repository) {
        this.repository = repository;
    }

    // 이미 처리된 이벤트면 skip, 아니면 실행 후 처리 완료 기록
    public void processOnce(String eventId, Runnable task) {
        if (repository.existsByEventId(eventId)) {
            log.debug("Duplicate event skipped: eventId={}", eventId);
            return;
        }

        task.run();
        repository.save(eventId);
        log.debug("Event processed: eventId={}", eventId);
    }
}
