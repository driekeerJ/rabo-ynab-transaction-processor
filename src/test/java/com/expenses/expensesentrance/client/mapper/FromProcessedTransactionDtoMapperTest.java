package com.expenses.expensesentrance.client.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.expensesentrance.client.model.ProcessedTransactionDto;
import com.expenses.expensesentrance.client.model.SubTransactionDto;
import com.expenses.expensesentrance.common.model.ProcessedTransaction;
import com.expenses.expensesentrance.common.model.SubTransaction;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class FromProcessedTransactionDtoMapperTest {

    @Mock
    private FromSubTransactionDtoMapper fromSubTransactionDtoMapper;

    @Mock
    private List<SubTransaction> subTransactions;

    @InjectMocks
    private FromProcessedTransactionDtoMapper sut;

    @Before
    public void setup() {
        when(fromSubTransactionDtoMapper.map(any(List.class))).thenReturn(subTransactions);
    }

    @Test
    public void map() {
        final ProcessedTransactionDto input = createProcessedTransactionDto();
        final ProcessedTransaction result = sut.map(input);

        assertThat(result.getAmount()).isEqualTo(input.getAmount());
        assertThat(result.getDate()).isEqualTo(input.getDate());
        assertThat(result.getId()).isEqualTo(input.getId());
        assertThat(result.getImportId()).isEqualTo(input.getImportId());
        assertThat(result.getMemo()).isEqualTo(input.getMemo());
        assertThat(result.getSubtransactions()).isEqualTo(subTransactions);
    }

    private ProcessedTransactionDto createProcessedTransactionDto() {
        final ProcessedTransactionDto processedTransactionDto = new ProcessedTransactionDto();
        processedTransactionDto.setAmount(10);
        processedTransactionDto.setDate(LocalDate.MAX);
        processedTransactionDto.setId("ID");
        processedTransactionDto.setImportId("IMPORT");
        processedTransactionDto.setMemo("MEMO");
        processedTransactionDto.setSubtransactions(Collections.singletonList(new SubTransactionDto()));

        return processedTransactionDto;
    }

}
