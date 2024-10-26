package com.mosope.expenseTracker.wallet;

import com.mosope.expenseTracker.wallet.service.GetUserWallet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final GetUserWallet getUserWallet;

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getWallet(@RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> response = getUserWallet.getUserWallet(authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
