package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record InstantBuyStartedEvent(
        Long auctionId,
        Long productId,
        String sellerId,
        String buyerId,
        Long price,
        LocalDateTime expiresAt
) {
}
