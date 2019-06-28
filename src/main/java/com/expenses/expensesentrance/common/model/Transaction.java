package com.expenses.expensesentrance.common.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Transaction {

    private String account;

    private LocalDate date;

    private double amount;

    private String counterPartyAccount;

    private String counterPartyName;

    private String description;

}
