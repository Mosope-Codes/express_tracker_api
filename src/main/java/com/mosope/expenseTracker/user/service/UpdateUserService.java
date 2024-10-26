package com.mosope.expenseTracker.user.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import com.mosope.expenseTracker.user.dto.UpdateUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UpdateUserService {
    private final UserRepository userRepository;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> updateUser(UpdateUserRequestDto updateUserRequestDto, String authorizationHeader) {
        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        Users user = userRepository.findByEmail(userEmail);
        user.setUsername(updateUserRequestDto.getUsername());
        Users savedUser = userRepository.save(user);
        return ResponseUtil.createSuccessResponse("User updated successfully", savedUser);
    }
}
