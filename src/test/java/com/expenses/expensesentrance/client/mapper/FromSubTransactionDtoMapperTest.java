package com.expenses.expensesentrance.client.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.expenses.expensesentrance.client.model.SubTransactionDto;
import com.expenses.expensesentrance.common.model.SubTransaction;

public class FromSubTransactionDtoMapperTest {

    private final FromSubTransactionDtoMapper sut = new FromSubTransactionDtoMapper();

    @Test
    public void map() {
        final SubTransactionDto input = createSubTransactionDto();
        final SubTransaction result = sut.map(input);

        assertThat(result.getId()).isEqualTo(input.getId());
        assertThat(result.getTransactionId()).isEqualTo(input.getTransaction_id());
    }

    private SubTransactionDto createSubTransactionDto() {
        final SubTransactionDto subTransactionDto = new SubTransactionDto();
        subTransactionDto.setId("ID");
        subTransactionDto.setTransaction_id("TRID");
        return subTransactionDto;
    }
}
