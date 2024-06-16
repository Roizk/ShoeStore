package com.example.shoestore.Domain.Response;

import lombok.Builder;

@Builder
public record RegistrationResponseWrapper(int status,
                                          String message,
                                          boolean success,
                                          RegistResponse user) {
}
