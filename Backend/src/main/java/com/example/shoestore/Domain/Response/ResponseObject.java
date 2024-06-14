package com.example.shoestore.Domain.Response;

import lombok.Builder;

@Builder
public record ResponseObject(int status,
                             String message,
                             Object data) {
}