package com.eolma.common.event.payload;

import java.time.LocalDateTime;

public record BidPlacedEvent(
        Long bidId,
        Long auctionId,
        Long bidderId,
        Long bidAmount,
        Long previousHighestBid,
        int bidSequence,
        LocalDateTime placedAt
) {
}
