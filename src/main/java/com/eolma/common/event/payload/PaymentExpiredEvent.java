package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record PaymentExpiredEvent(
        String paymentId,
        Long auctionId,
        String buyerId,
        Long amount,
        LocalDateTime expiredAt
) {
}
