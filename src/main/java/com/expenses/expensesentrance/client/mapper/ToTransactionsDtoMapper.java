package com.expenses.expensesentrance.client.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.TransactionsDto;
import com.expenses.expensesentrance.common.ContextMapper;
import com.expenses.expensesentrance.common.model.Transaction;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ToTransactionsDtoMapper implements ContextMapper<String, List<Transaction>, TransactionsDto> {

    private final ToTransactionDtoMapper toTransactionDtoMapper;

    @Override
    public TransactionsDto map(final String account, final List<Transaction> input) {
        return TransactionsDto.builder()
                .transactions(toTransactionDtoMapper.map(account, input))
                .build();
    }
}
