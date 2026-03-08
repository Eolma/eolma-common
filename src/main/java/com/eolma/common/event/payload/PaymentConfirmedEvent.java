package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentConfirmedEvent(
        String paymentId,
        Long auctionId,
        Long productId,
        String buyerId,
        String sellerId,
        Long amount,
        String paymentKey,
        LocalDateTime confirmedAt
) {
}
