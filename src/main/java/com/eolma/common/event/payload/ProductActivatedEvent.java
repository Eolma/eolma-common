package com.eolma.common.event.payload;

import java.util.List;

public record ProductActivatedEvent(
        Long productId,
        String sellerId,
        String title,
        String description,
        String category,
        String conditionGrade,
        Long startingPrice,
        Long instantPrice,
        Integer instantBuyLockPercent,
        Long reservePrice,
        Long minBidUnit,
        String endType,
        Integer durationHours,
        Integer maxBidCount,
        List<String> imageUrls
) {
}
