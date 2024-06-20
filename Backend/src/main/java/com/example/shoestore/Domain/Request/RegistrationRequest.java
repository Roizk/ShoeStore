package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Address.Address;

public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  Address address,
                                  String userName
                                  ) {

}
