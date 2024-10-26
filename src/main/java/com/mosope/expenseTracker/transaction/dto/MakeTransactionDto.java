package com.mosope.expenseTracker.transaction.dto;

import com.mosope.expenseTracker.transaction.Transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MakeTransactionDto {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "category cannot be blank")
    private String category;
//    @NotBlank(message = "amount cannot be blank")
    private Double amount;
//    @NotBlank(message = "choose either CREDIT or DEBIT")
    private Transaction.Type type;
}
