package com.expenses.expensesentrance.common.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedTransaction {

    private String id;
    private LocalDate date;
    private double amount;
    private String memo;
    private String importId;
    private List<SubTransaction> subtransactions;

}
