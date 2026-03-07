package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record InstantBuyStartedEvent(
        Long auctionId,
        Long productId,
        Long sellerId,
        Long buyerId,
        Long price,
        LocalDateTime expiresAt
) {
}
