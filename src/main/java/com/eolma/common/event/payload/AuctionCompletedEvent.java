package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record AuctionCompletedEvent(
        Long auctionId,
        Long productId,
        String sellerId,
        String winnerId,
        Long finalPrice,
        int totalBidCount,
        LocalDateTime completedAt
) {
}
