package com.expenses.expensesentrance.common.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedTransactionData {

    private List<String> transactionIds;

    private List<ProcessedTransaction> transactions;

}
