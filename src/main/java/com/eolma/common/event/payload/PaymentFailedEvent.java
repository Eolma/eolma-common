package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentFailedEvent(
        String paymentId,
        Long auctionId,
        String buyerId,
        Long amount,
        String failureReason,
        LocalDateTime failedAt
) {
}
