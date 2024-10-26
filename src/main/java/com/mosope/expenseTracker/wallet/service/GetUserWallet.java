package com.mosope.expenseTracker.wallet.service;

import com.mosope.expenseTracker.ResponseUtil;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.transaction.TransactionRepository;
import com.mosope.expenseTracker.user.service.GetUserDetailsService;
import com.mosope.expenseTracker.wallet.Wallet;
import com.mosope.expenseTracker.wallet.WalletRepository;
import com.mosope.expenseTracker.wallet.dto.GetWalletResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetUserWallet {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final GetUserDetailsService getUserDetailsService;

    public Map<String, Object> getUserWallet(String authorizationHeader) {
        String userEmail = getUserDetailsService.getEmailFromToken(authorizationHeader);
        Wallet userWallet = walletRepository.findByUserEmail(userEmail);

        Double credits = calculateSum(userEmail, Transaction.Type.CREDIT);
        Double debits = calculateSum(userEmail, Transaction.Type.DEBIT);
        GetWalletResponseDto wallet = new GetWalletResponseDto();
        wallet.setBalance(userWallet.getBalance());
        wallet.setTotalCredit(credits);
        wallet.setTotalDebit(debits);
        wallet.setStatus(userWallet.getStatus());
        return ResponseUtil.createSuccessResponse("Wallet", wallet);
    }


    public Double calculateSum(String userEmail, Transaction.Type type) {
        List<Transaction> transactions = transactionRepository.findAllByUserEmail(userEmail);
        Double transactionCreditOrDebit = 0.00;

        for (Transaction transaction : transactions) {
            if(transaction.getType().equals(type)){
                transactionCreditOrDebit += transaction.getAmount();
            }
        }
        return transactionCreditOrDebit;
    }
}
