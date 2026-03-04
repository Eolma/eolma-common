package com.eolma.common.event.payload;

public record UserRegisteredEvent(
        Long userId,
        String email,
        String nickname
) {
}
