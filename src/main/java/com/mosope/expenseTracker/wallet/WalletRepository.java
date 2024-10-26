package com.mosope.expenseTracker.wallet;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WalletRepository extends PagingAndSortingRepository<Wallet, Integer> {
    Wallet save(Wallet wallet);
    Wallet findById(Long id);
    void deleteById(Long id);
    Wallet findByUserId(Long userId);
    Wallet findByUserEmail(String email);
}
