package com.mosope.expenseTracker.transaction.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAllTransactionsService {
    private final TransactionRepository transactionRepository;

    public Map<String, Object> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return ResponseUtil.createSuccessResponse("Transactions", transactions);
    }

    public Map<String, Object> filterTransactionsByMonth(int month) {
        List<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> filteredTransactions = filterTransaction(month, transactions);
        return ResponseUtil.createSuccessResponse("Transactions for the month of " + Month.of(month).name(), filteredTransactions);
    }



    public List<Transaction> filterTransaction(int month, List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction.getCreatedAt().getMonthValue() == month) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

}
