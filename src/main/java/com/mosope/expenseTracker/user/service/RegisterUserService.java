package com.mosope.expenseTracker.user.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import com.mosope.expenseTracker.user.dto.RegisterUserRequestDto;
import com.mosope.expenseTracker.wallet.service.CreateWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final UserRepository userRepository;
    private final CreateWalletService createWalletService;

    public Map<String, Object> registerUser(RegisterUserRequestDto registerUserRequestDto) {
        Users user = new Users();
        user.setFirstName(registerUserRequestDto.getFirstName());
        user.setLastName(registerUserRequestDto.getLastName());
        user.setUsername(registerUserRequestDto.getUsername());
        user.setEmail(registerUserRequestDto.getEmail());
        user.setPassword(hashPassword(registerUserRequestDto.getPassword()));
        Users createdUser = userRepository.save(user);
        createWalletService.createWallet(createdUser);
        return ResponseUtil.createSuccessResponse("User registered successfully");
    }

    String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
