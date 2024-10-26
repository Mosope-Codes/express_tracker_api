package com.mosope.expenseTracker.transaction;

import com.mosope.expenseTracker.transaction.dto.MakeTransactionDto;
import com.mosope.expenseTracker.transaction.service.GetAllTransactionsService;
import com.mosope.expenseTracker.transaction.service.GetTransactionByIdService;
import com.mosope.expenseTracker.transaction.service.GetTransactionByUserService;
import com.mosope.expenseTracker.transaction.service.MakeTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final MakeTransactionService makeTransactionService;
    private final GetAllTransactionsService getAllTransactionsService;
    private final GetTransactionByIdService getTransactionByIdService;
    private final GetTransactionByUserService getTransactionByUserService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransaction(@Valid @RequestBody MakeTransactionDto makeTransactionDto, @RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> response = makeTransactionService.makeTransaction(makeTransactionDto, authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, Object>> getAllTransactions(Pageable pageable) {
        Map<String, Object> response = getAllTransactionsService.getAllTransactions(pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getTransactionById(@PathVariable("id") Long id) {
        Map<String, Object> response = getTransactionByIdService.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getTransaction(@RequestHeader("Authorization") String authorizationHeader, Pageable pageable) {
        Map<String, Object> response = getTransactionByUserService.getTransactionByUserId(authorizationHeader, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/transactions/month")
    public ResponseEntity<Map<String, Object>> filterTransactionsByMonth(@RequestParam("month") int month) {
        Map<String, Object> response = getAllTransactionsService.filterTransactionsByMonth(month);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/transactions/month/user")
    public ResponseEntity<Map<String, Object>> filterUserTransactionsByMonth(@RequestParam("month") int month, @RequestHeader("Authorization") String authorizationHeader) {
        Map<String, Object> response = getTransactionByUserService.filterUserTransactionsByMonth(month, authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
