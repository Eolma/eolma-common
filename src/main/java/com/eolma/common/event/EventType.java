package com.eolma.common.event;

public final class EventType {

    private EventType() {
    }

    // User
    public static final String USER_REGISTERED = "user.registered";

    // Product
    public static final String PRODUCT_ACTIVATED = "product.activated";
    public static final String PRODUCT_CANCELLED = "product.cancelled";

    // Auction
    public static final String AUCTION_STARTED = "auction.started";
    public static final String AUCTION_COMPLETED = "auction.completed";
    public static final String AUCTION_FAILED = "auction.failed";
    public static final String BID_PLACED = "bid.placed";

    // Payment
    public static final String PAYMENT_CONFIRMED = "payment.confirmed";
    public static final String PAYMENT_FAILED = "payment.failed";
    public static final String PAYMENT_EXPIRED = "payment.expired";
    public static final String PAYMENT_CANCELLED = "payment.cancelled";
}
