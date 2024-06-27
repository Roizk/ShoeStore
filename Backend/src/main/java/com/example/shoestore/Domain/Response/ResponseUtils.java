package com.example.shoestore.Domain.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<ResponseObject> buildSuccessResponse(Object data, String message) {
        ResponseObject response = ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<ResponseObject> buildErrorResponse(HttpStatus status, String message) {
        ResponseObject response = ResponseObject.builder()
                .status(status.value())
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }
}