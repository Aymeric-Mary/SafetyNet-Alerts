package com.safetynet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class AppExceptionHandler {

    private final String ERROR = "error";

    @ExceptionHandler(NoSuchFireStationException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchFireStationException(NoSuchFireStationException e) {
        HashMap<String, Object> body = new HashMap<>();
        body.put(ERROR, "NO_SUCH_FIRESTATION");
        if (!Objects.isNull(e.getStation())) {
            body.put("station", e.getStation());
        }
        if (!Objects.isNull(e.getAddress())) {
            body.put("address", e.getAddress());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        ERROR, "ILLEGAL_ARGUMENT",
                        "message", e.getMessage()
                )
        );
    }

}