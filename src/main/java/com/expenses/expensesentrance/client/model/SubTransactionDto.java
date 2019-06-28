package com.expenses.expensesentrance.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubTransactionDto {

    private String id;

    private String transaction_id;

}
