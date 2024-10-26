package com.mosope.expenseTracker.transaction.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.transaction.TransactionRepository;
import com.mosope.expenseTracker.transaction.dto.MakeTransactionDto;
import com.mosope.expenseTracker.user.UserRepository;
import com.mosope.expenseTracker.user.Users;
import com.mosope.expenseTracker.user.service.GetUserDetailsService;
import com.mosope.expenseTracker.wallet.Wallet;
import com.mosope.expenseTracker.wallet.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MakeTransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> makeTransaction(MakeTransactionDto makeTransactionDto, String authorizationHeader) {

        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        Users user = userRepository.findByEmail(userEmail);
        Transaction transaction = new Transaction();
        transaction.setName(makeTransactionDto.getName());
        transaction.setCategory(makeTransactionDto.getCategory());
        transaction.setAmount(makeTransactionDto.getAmount());
        transaction.setType(makeTransactionDto.getType());
        transaction.setUser(user);
        Transaction transactionMade = transactionRepository.save(transaction);
        updateBalance(transactionMade, userEmail);
        return ResponseUtil.createSuccessResponse("transaction successfully made", transactionMade);
    }

    private void updateBalance(Transaction transaction, String userEmail) {
        Wallet wallet = walletRepository.findByUserEmail(userEmail);
        Double balance = wallet.getBalance();
        if(transaction.getType().equals(Transaction.Type.CREDIT)){
            balance = balance + transaction.getAmount();
            wallet.setBalance(balance);
        }
        if(transaction.getType().equals(Transaction.Type.DEBIT)){
            balance = balance - transaction.getAmount();
            wallet.setBalance(balance);
        }
        walletRepository.save(wallet);
    }
}
