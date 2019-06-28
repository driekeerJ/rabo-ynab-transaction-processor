package com.expenses.expensesentrance.client.mapper;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.ProcessedDataDto;
import com.expenses.expensesentrance.common.Mapper;
import com.expenses.expensesentrance.common.model.ProcessedTransactionData;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FromProcessedDataDtoMapper implements Mapper<ProcessedDataDto, ProcessedTransactionData> {

    private final FromProcessedTransactionDtoMapper fromProcessedTransactionDtoMapper;

    @Override
    public ProcessedTransactionData map(final ProcessedDataDto input) {
        return ProcessedTransactionData.builder()
                .transactionIds(input.getTransactionIds())
                .transactions(fromProcessedTransactionDtoMapper.map(input.getTransactions()))
                .build();
    }
}
