package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentCancelledEvent(
        Long paymentId,
        Long auctionId,
        Long buyerId,
        Long amount,
        LocalDateTime cancelledAt
) {
}
