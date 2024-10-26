package com.mosope.expenseTracker.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
    @NotBlank
    private String username;
}
