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
            log.error("NoSuchFireStationException station = " + e.getStation());
        }
        if (!Objects.isNull(e.getAddress())) {
            body.put("address", e.getAddress());
            log.error("NoSuchFireStationException address = " + e.getAddress());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(NoSuchAddressException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchAddressException(NoSuchAddressException e) {
        log.error("NoSuchAddressException address = " + e.getAddress());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        ERROR, "NO_SUCH_ADDRESS",
                        "address", e.getAddress()
                )
        );
    }

    @ExceptionHandler(NoSuchMedicalRecordException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchMedicalRecordException(NoSuchMedicalRecordException e) {
        log.error("NoSuchMedicalRecordException firstName = " + e.getFirstName() + " lastName = " + e.getLastName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        ERROR, "NO_SUCH_MEDICAL_RECORD",
                        "firstName", e.getFirstName(),
                        "lastName", e.getLastName()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException message = " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        ERROR, "ILLEGAL_ARGUMENT",
                        "message", e.getMessage()
                )
        );
    }

    @ExceptionHandler(MedicalRecordAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleMedicalRecordAlreadyExistException(MedicalRecordAlreadyExistException e) {
        log.error("MedicalRecordAlreadyExistException firstName = " + e.getFirstName() + " lastName = " + e.getLastName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        ERROR, "MEDICAL_RECORD_ALREADY_EXIST",
                        "firstName", e.getFirstName(),
                        "lastName", e.getLastName()
                )
        );
    }

    @ExceptionHandler(PersonAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handlePersonAlreadyExistException(PersonAlreadyExistException e) {
        log.error("PersonAlreadyExistException firstName = " + e.getFirstName() + " lastName = " + e.getLastName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        ERROR, "PERSON_ALREADY_EXIST",
                        "firstName", e.getFirstName(),
                        "lastName", e.getLastName()
                )
        );
    }

    @ExceptionHandler(FireStationAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleFireStationAlreadyExistException(FireStationAlreadyExistException e) {
        log.error("FireStationAlreadyExistException address = " + e.getAddress());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        ERROR, "FIRESTATION_ALREADY_EXIST",
                        "address", e.getAddress()
                )
        );
    }

    @ExceptionHandler(NoSuchPersonException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchPersonException(NoSuchPersonException e) {
        log.error("NoSuchPersonException firstName = " + e.getFirstName() + " lastName = " + e.getLastName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        ERROR, "NO_SUCH_PERSON",
                        "firstName", e.getFirstName(),
                        "lastName", e.getLastName()
                )
        );
    }

}