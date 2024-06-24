package com.example.shoestore.Domain.Response;

import com.example.shoestore.Domain.Model.Address.Address;
import lombok.Builder;

@Builder
public record ProfileResponse(String email,
                              String userName,
                              String lastName,
                              String firstName ,
                              Address address,
                              Byte image) {
}
