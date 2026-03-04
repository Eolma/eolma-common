package com.eolma.common.idempotency;

// 처리된 이벤트 ID 저장소 인터페이스
// 각 서비스에서 Redis/DB 기반으로 구현
public interface ProcessedEventRepository {

    boolean existsByEventId(String eventId);

    void save(String eventId);
}
