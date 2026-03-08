package com.eolma.common.event.payload;

public record UserRegisteredEvent(
        String userId,
        String email,
        String nickname
) {
}
