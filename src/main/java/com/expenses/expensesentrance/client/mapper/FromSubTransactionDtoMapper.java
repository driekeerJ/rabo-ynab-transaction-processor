package com.expenses.expensesentrance.client.mapper;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.SubTransactionDto;
import com.expenses.expensesentrance.common.Mapper;
import com.expenses.expensesentrance.common.model.SubTransaction;

@Component
public class FromSubTransactionDtoMapper implements Mapper<SubTransactionDto, SubTransaction> {

    @Override
    public SubTransaction map(final SubTransactionDto input) {
        return SubTransaction.builder()
                .id(input.getId())
                .transactionId(input.getTransaction_id())
                .build();
    }
}
