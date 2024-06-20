package com.example.shoestore.Domain.Model.Token;

import com.example.shoestore.Domain.Model.User.User;
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
    private User user; // or private User user;
    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }
    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }


}
