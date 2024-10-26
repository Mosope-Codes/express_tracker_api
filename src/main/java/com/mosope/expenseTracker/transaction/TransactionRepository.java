package com.mosope.expenseTracker.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    Transaction save(Transaction transaction);
    Transaction findById(Long id);
    void deleteById(Long id);
    Page<Transaction> findByUserId(Long userId, Pageable pageable);
    Page<Transaction> findAllByUserEmail(String userEmail, Pageable pageable);
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findAllByUserEmail(String email);
    Transaction findByUserEmail(String email);
    List<Transaction> findAll();
}
