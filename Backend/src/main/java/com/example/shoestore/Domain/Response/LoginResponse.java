package com.example.shoestore.Domain.Response;

import lombok.Builder;


@Builder
public record LoginResponse(String username
        , String name
        , String role
        , String token
        , String message) {
}
