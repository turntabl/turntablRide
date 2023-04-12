package com.ttrides.turntablRides.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttrides.turntablRides.exception.CustomAuthenticationException;
import com.ttrides.turntablRides.model.response.TTResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication scheme.
     * <p>
     * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
     * attribute named
     * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
     * with the requested target URL before calling this method.
     * <p>
     * Implementations should modify the headers on the <code>ServletResponse</code> as
     * necessary to commence the authentication process.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        TTResponse ttResponse;
        if (authException instanceof CustomAuthenticationException customAuthException) {
            ttResponse = TTResponse.builder().statusCode(HttpServletResponse.SC_UNAUTHORIZED).statusText("Unauthorized").message(customAuthException.getMessage()).build();

        }else {
            ttResponse = TTResponse.builder().statusCode(HttpServletResponse.SC_UNAUTHORIZED).statusText("Unauthorized").message("Invalid or expired token").build();

        }
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), ttResponse);
    }
}
