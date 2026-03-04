package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentConfirmedEvent(
        Long paymentId,
        Long auctionId,
        Long buyerId,
        Long amount,
        String paymentKey,
        LocalDateTime confirmedAt
) {
}
