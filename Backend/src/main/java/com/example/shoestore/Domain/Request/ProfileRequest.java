package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Address.Address;
import lombok.Builder;

@Builder
public record ProfileRequest (String email,
                              String userName,
                              String lastName,
                              String firstName,
                              Address address ){}
