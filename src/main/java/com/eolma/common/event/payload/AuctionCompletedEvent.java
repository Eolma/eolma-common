package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record AuctionCompletedEvent(
        Long auctionId,
        Long productId,
        Long winnerId,
        Long finalPrice,
        int totalBidCount,
        LocalDateTime completedAt
) {
}
