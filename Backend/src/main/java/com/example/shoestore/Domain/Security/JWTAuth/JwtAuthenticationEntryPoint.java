package com.example.shoestore.Domain.Security.JWTAuth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Ghi lại thông báo lỗi
        System.out.println("Unauthorized error: " + authException.getMessage());

        // Trả về mã lỗi 401 và thông báo lỗi JSON
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request");
    }
}