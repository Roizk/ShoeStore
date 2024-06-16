package com.example.shoestore.Domain.Response;

import lombok.Builder;

@Builder
public record RegistResponse(String firstName,
                             String lastName,
                             String email
) {
}
