package com.mosope.expenseTracker.wallet.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.user.Users;
import com.mosope.expenseTracker.wallet.Wallet;
import com.mosope.expenseTracker.wallet.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateWalletService {
    private final WalletRepository walletRepository;

    public void createWallet(Users user) {

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.00);
        wallet.setStatus("The Journey Begins...");
        walletRepository.save(wallet);
    }
}
