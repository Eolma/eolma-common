package com.eolma.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 비즈니스 예외 (EolmaException)
    @ExceptionHandler(EolmaException.class)
    public ProblemDetail handleEolmaException(EolmaException e) {
        ErrorType errorType = e.getErrorType();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.valueOf(errorType.getStatus()),
                e.getDetail()
        );
        problemDetail.setType(URI.create(errorType.getType()));
        problemDetail.setTitle(errorType.getTitle());

        if (errorType.getStatus() >= 500) {
            log.error("Server error: type={}, detail={}", errorType.getType(), e.getDetail(), e);
        } else {
            log.warn("Client error: type={}, detail={}", errorType.getType(), e.getDetail());
        }

        return problemDetail;
    }

    // JSON 파싱 오류 (잘못된 enum, 타입 불일치, 잘못된 JSON 형식)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("Bad request - message not readable: {}", e.getMostSpecificCause().getMessage());
        return buildClientError("Request body is invalid or contains unsupported values.");
    }

    // @Valid 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException e) {
        String details = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validation failed: {}", details);
        return buildClientError(details);
    }

    // 필수 헤더 누락 (X-User-Id 등)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ProblemDetail handleMissingHeader(MissingRequestHeaderException e) {
        log.warn("Missing required header: {}", e.getHeaderName());
        return buildClientError("Missing required header: " + e.getHeaderName());
    }

    // 경로 변수/파라미터 타입 불일치
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        log.warn("Type mismatch: parameter={}, value={}", e.getName(), e.getValue());
        return buildClientError("Invalid parameter '" + e.getName() + "': " + e.getValue());
    }

    // 미처리 예외 (진짜 서버 오류)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred"
        );
        problemDetail.setType(URI.create(ErrorType.INTERNAL_ERROR.getType()));
        problemDetail.setTitle(ErrorType.INTERNAL_ERROR.getTitle());
        return problemDetail;
    }

    private ProblemDetail buildClientError(String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, detail
        );
        problemDetail.setType(URI.create(ErrorType.INVALID_REQUEST.getType()));
        problemDetail.setTitle(ErrorType.INVALID_REQUEST.getTitle());
        return problemDetail;
    }
}
