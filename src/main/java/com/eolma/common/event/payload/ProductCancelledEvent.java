package com.eolma.common.event.payload;

public record ProductCancelledEvent(
        Long productId,
        Long sellerId,
        String reason
) {
}
