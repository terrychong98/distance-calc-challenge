package com.challenge.authorization.interceptor;

import com.challenge.authorization.model.AuthEntity;
import com.challenge.util.Encryptor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Example: Check for a token in header
        String token = request.getHeader("Authorization");

        if (token == null || !isValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false;
        }

        return true;
    }

    private boolean isValid(String token) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthEntity entity = mapper.readValue(Encryptor.decrypt(token), AuthEntity.class);
        return StringUtils.equals(entity.getUsername(), "username")
                && StringUtils.equals(entity.getPassword(), "password");
    }
}