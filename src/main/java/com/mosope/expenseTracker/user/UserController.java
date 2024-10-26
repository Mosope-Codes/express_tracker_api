package com.mosope.expenseTracker.user;

import com.mosope.expenseTracker.user.dto.RegisterUserRequestDto;
import com.mosope.expenseTracker.user.dto.UpdateUserRequestDto;
import com.mosope.expenseTracker.user.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final RegisterUserService registerUserService;
    private final GetAllUsersService getAllUsersService;
    private final GetUserDetailsService getUserDetailsService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        Map<String, Object> user = registerUserService.registerUser(registerUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers(Pageable pageable) {
        Map<String, Object> users = getAllUsersService.getAllUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> user = getUserDetailsService.getUserDetails(authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto, @RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> user = updateUserService.updateUser(updateUserRequestDto,authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> user = deleteUserService.deleteUser(authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
