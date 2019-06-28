package com.expenses.expensesentrance.core.ynab;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenses.expensesentrance.client.YnabClient;
import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YnabService {

    private final YnabClient ynabClient;

    public Data processTransactions(final List<Transaction> transactions, final String token,
            final String budget, final String account) {

        return ynabClient.processTransactions(transactions, token, budget, account);

    }

}
