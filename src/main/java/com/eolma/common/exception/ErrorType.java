package com.eolma.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    // Common
    INVALID_REQUEST("/errors/invalid-request", "Invalid Request", 400),
    UNAUTHORIZED("/errors/unauthorized", "Unauthorized", 401),
    FORBIDDEN("/errors/forbidden", "Forbidden", 403),
    NOT_FOUND("/errors/not-found", "Not Found", 404),
    INTERNAL_ERROR("/errors/internal-error", "Internal Server Error", 500),

    // User
    USER_NOT_FOUND("/errors/user-not-found", "User Not Found", 404),
    DUPLICATE_EMAIL("/errors/duplicate-email", "Duplicate Email", 409),

    // Product
    PRODUCT_NOT_FOUND("/errors/product-not-found", "Product Not Found", 404),

    // Auction
    AUCTION_NOT_FOUND("/errors/auction-not-found", "Auction Not Found", 404),
    AUCTION_CLOSED("/errors/auction-closed", "Auction Closed", 409),
    BID_TOO_LOW("/errors/bid-too-low", "Bid Too Low", 400),

    // Payment
    PAYMENT_NOT_FOUND("/errors/payment-not-found", "Payment Not Found", 404),
    PAYMENT_ALREADY_EXISTS("/errors/payment-already-exists", "Payment Already Exists", 409),
    PAYMENT_AMOUNT_MISMATCH("/errors/payment-amount-mismatch", "Payment Amount Mismatch", 400),
    PAYMENT_EXPIRED("/errors/payment-expired", "Payment Expired", 409),
    PAYMENT_NOT_PENDING("/errors/payment-not-pending", "Payment Not Pending", 409),
    PAYMENT_FAILED("/errors/payment-failed", "Payment Failed", 502);

    private final String type;
    private final String title;
    private final int status;

    ErrorType(String type, String title, int status) {
        this.type = type;
        this.title = title;
        this.status = status;
    }
}
