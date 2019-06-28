package com.expenses.expensesentrance.common.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubTransaction {
    private String id;
    private String transactionId;
}
