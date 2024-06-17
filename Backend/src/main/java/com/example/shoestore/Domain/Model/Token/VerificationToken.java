package com.example.shoestore.Domain.Model.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "verificationToken")
public class VerificationToken {
    @Id
    private String id; // MongoDB uses String type for its IDs
    @Indexed(unique = true)
    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME = 15;
    // In MongoDB, you might store the related user's ID or embed the User document
    @DBRef
    private String userId; // or private User user;


}
