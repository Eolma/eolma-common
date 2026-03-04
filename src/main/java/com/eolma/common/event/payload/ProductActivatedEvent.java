package com.eolma.common.event.payload;

import java.util.List;

public record ProductActivatedEvent(
        Long productId,
        Long sellerId,
        String title,
        String description,
        String category,
        String conditionGrade,
        Long startingPrice,
        Long instantPrice,
        Long reservePrice,
        Long minBidUnit,
        String endType,
        String endValue,
        List<String> imageUrls
) {
}
