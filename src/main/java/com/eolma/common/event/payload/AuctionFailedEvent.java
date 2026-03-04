package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record AuctionFailedEvent(
        Long auctionId,
        Long productId,
        String reason,
        LocalDateTime failedAt
) {
}
