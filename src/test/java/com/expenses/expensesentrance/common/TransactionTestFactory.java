package com.expenses.expensesentrance.common;

import java.time.LocalDate;

import com.expenses.expensesentrance.common.model.Transaction;

public class TransactionTestFactory {
    public static Transaction createTransaction() {
        return Transaction.builder()
                .amount(2.0)
                .counterPartyAccount("CPA")
                .counterPartyName("NAME")
                .date(LocalDate.MAX)
                .description("Descr")
                .build();
    }
}
