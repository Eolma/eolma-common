package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentFailedEvent(
        Long paymentId,
        Long auctionId,
        Long buyerId,
        Long amount,
        String failureReason,
        LocalDateTime failedAt
) {
}
