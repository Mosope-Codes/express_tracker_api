package com.mosope.expenseTracker.wallet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetWalletResponseDto {

//    @NotBlank(message = "balance cannot be blank")
    private Double balance;

//    @NotBlank(message = "total credit cannot be blank")
    private Double totalCredit;

//    @NotBlank(message = "total debit cannot be blank")
    private Double totalDebit;

    @NotBlank(message = "status cannot be blank")
    private String status;
}
