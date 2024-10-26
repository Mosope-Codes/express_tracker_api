package com.mosope.expenseTracker.security.manager;

import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Users user = userRepository.findByEmail(authentication.getName());
        if(user == null){
            throw new BadCredentialsException("Email not found");
        }
        if(!comparePassword(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("Password not match");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }

    private Boolean comparePassword(String password, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.matches(password, hashedPassword);
    }
}
