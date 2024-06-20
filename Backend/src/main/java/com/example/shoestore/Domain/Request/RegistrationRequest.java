package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Address.Address;
import lombok.Builder;

@Builder
public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String userName,
                                  Address address,

                                  String password
                                  ) {

}
