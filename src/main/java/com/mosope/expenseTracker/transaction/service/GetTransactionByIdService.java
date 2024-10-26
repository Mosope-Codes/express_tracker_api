package com.mosope.expenseTracker.transaction.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.transaction.TransactionRepository;
import com.mosope.expenseTracker.user.service.GetUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetTransactionByIdService {
    private final TransactionRepository transactionRepository;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id);
        return ResponseUtil.createSuccessResponse("Transaction", transaction);
    }
}
