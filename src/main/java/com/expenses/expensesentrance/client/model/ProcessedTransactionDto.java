package com.expenses.expensesentrance.client.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessedTransactionDto {

    private String id;
    private LocalDate date;
    private double amount;
    private String memo;
    private String importId;
    private List<SubTransactionDto> subtransactions;
    private String matched_transaction_id;

}
