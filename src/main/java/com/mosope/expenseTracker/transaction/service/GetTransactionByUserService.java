package com.mosope.expenseTracker.transaction.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.transaction.TransactionRepository;
import com.mosope.expenseTracker.user.service.GetUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetTransactionByUserService {
    private final TransactionRepository transactionRepository;
    private final GetAllTransactionsService getAllTransactionsService;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> getTransactionByUserId(String authorizationHeader, Pageable pageable) {
        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        Page<Transaction> transactions = transactionRepository.findAllByUserEmail(userEmail, pageable);
        return ResponseUtil.createSuccessResponse("Transactions", transactions);
    }

    public Map<String, Object> filterUserTransactionsByMonth(int month, String authorizationHeader) {
        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        List<Transaction> transactions = transactionRepository.findAllByUserEmail(userEmail);
        List<Transaction> filteredTransactions = getAllTransactionsService.filterTransaction(month, transactions);
        return ResponseUtil.createSuccessResponse("Transactions for the month of " + Month.of(month).name(), filteredTransactions);
    }
}
