package com.mosope.expenseTracker.user.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final UserRepository userRepository;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> deleteUser(String authorizationHeader) {
        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        userRepository.deleteByEmail(userEmail);
        return ResponseUtil.createSuccessResponse("User deleted successfully");
    }
}
