package com.mosope.expenseTracker.user.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.security.SecurityConstants;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetUserDetailsService {
    private final UserRepository userRepository;

    public Map<String, Object> getUserDetails(String authorizationHeader) {
        String userEmail = getEmailFromToken(authorizationHeader);
        Users user = userRepository.findByEmail(userEmail);
        return ResponseUtil.createSuccessResponse("User", user);
    }

    public String getEmailFromToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        String userEmail = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return userEmail;
    }
}
