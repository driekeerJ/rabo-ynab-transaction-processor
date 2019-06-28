package com.expenses.expensesentrance.client.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransactionDto {

    private String account_id;

    private String payee_name;

    private String date;

    private int amount;

    private String memo;

    private String cleared;

    private UUID import_id;

}
