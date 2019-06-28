package com.expenses.expensesentrance.client.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.expensesentrance.client.model.ProcessedDataDto;
import com.expenses.expensesentrance.client.model.ProcessedTransactionDto;
import com.expenses.expensesentrance.common.model.ProcessedTransaction;
import com.expenses.expensesentrance.common.model.ProcessedTransactionData;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class FromProcessedDataDtoMapperTest {

    public static final String TRANSACTION_ID = "Transaction id";
    @Mock
    private FromProcessedTransactionDtoMapper fromProcessedTransactionDtoMapper;

    @Mock
    private List<ProcessedTransaction> processedTransactions;

    @InjectMocks
    private FromProcessedDataDtoMapper sut;

    @Before
    public void setup() {
        when(fromProcessedTransactionDtoMapper.map(any(List.class))).thenReturn(processedTransactions);
    }

    @Test
    public void map() {
        final ProcessedDataDto input = new ProcessedDataDto();
        input.setTransactions(Collections.singletonList(new ProcessedTransactionDto()));
        input.setTransactionIds(Collections.singletonList(TRANSACTION_ID));

        final ProcessedTransactionData result = sut.map(input);

        assertThat(result.getTransactions()).isEqualTo(processedTransactions);
        assertThat(result.getTransactionIds()).hasSize(1);
        assertThat(result.getTransactionIds().get(0)).isEqualTo(TRANSACTION_ID);
    }

}
