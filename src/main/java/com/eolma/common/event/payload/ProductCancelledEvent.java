package com.eolma.common.event.payload;

public record ProductCancelledEvent(
        Long productId,
        String sellerId,
        String reason
) {
}
