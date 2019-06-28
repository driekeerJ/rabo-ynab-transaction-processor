package com.expenses.expensesentrance.client.mapper;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.ProcessedTransactionDto;
import com.expenses.expensesentrance.common.Mapper;
import com.expenses.expensesentrance.common.model.ProcessedTransaction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FromProcessedTransactionDtoMapper implements Mapper<ProcessedTransactionDto, ProcessedTransaction> {

    private final FromSubTransactionDtoMapper fromSubTransactionDtoMapper;

    @Override
    public ProcessedTransaction map(final ProcessedTransactionDto input) {
        return ProcessedTransaction.builder()
                .amount(input.getAmount())
                .date(input.getDate())
                .id(input.getId())
                .importId(input.getImportId())
                .memo(input.getMemo())
                .subtransactions(fromSubTransactionDtoMapper.map(input.getSubtransactions()))
                .build();
    }
}
