package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record AuctionStartedEvent(
        Long auctionId,
        Long productId,
        Long startingPrice,
        Long instantPrice,
        Long reservePrice,
        Long minBidUnit,
        String endType,
        Integer durationHours,
        Integer maxBidCount,
        LocalDateTime startedAt
) {
}
