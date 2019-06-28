package com.expenses.expensesentrance.client.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransactionsDto {

    private List<TransactionDto> transactions;

}
