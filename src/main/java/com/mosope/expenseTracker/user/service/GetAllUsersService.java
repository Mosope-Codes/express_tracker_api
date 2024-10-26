package com.mosope.expenseTracker.user.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAllUsersService {
    private final UserRepository userRepository;

    public Map<String, Object> getAllUsers(Pageable pageable) {
        Page<Users> users = userRepository.findAll(pageable);
        return ResponseUtil.createSuccessResponse("users", users);
    }

}
