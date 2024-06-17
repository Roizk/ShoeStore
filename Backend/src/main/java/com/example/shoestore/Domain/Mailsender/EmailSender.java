package com.example.shoestore.Domain.Mailsender;

import com.example.shoestore.Domain.Model.User.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSender {
    void send(String url, User theUser) throws MessagingException
            , UnsupportedEncodingException;
}


