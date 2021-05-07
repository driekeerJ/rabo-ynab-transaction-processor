package com.expenses.expensesentrance.common.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedTransaction {

    private final String id;
    private final LocalDate date;
    private final double amount;
    private final String memo;
    private final String importId;
    private final List<SubTransaction> subtransactions;
    private final String matchedTransactionId;

}
