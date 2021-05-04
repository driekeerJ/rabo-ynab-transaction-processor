package com.expenses.expensesentrance.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessedDataDto {

    private List<String> transactionIds;

    private List<ProcessedTransactionDto> transactions;

    private List<String> duplicate_import_ids;
}
