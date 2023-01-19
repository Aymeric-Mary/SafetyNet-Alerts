package com.safetynet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class AppExceptionHandler {

    private final String ERROR = "error";

    @ExceptionHandler(NoSuchFireStationException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchFireStationException(NoSuchFireStationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        ERROR, "NO_SUCH_FIRESTATION",
                        "station", e.getStation()
                )
        );
    }

    @ExceptionHandler(NoSuchAddressException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchAddressException(NoSuchAddressException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        ERROR, "NO_SUCH_ADDRESS",
                        "address", e.getAddress()
                )
        );
    }

}