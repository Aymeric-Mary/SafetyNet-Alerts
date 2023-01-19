package com.safetynet.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoSuchAddressException extends RuntimeException {

    private final String address;

}
