package com.expenses.expensesentrance.client.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.expensesentrance.client.model.DataDto;
import com.expenses.expensesentrance.client.model.ProcessedDataDto;
import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.ProcessedTransactionData;

@RunWith(MockitoJUnitRunner.class)
public class FromDataDtoMapperTest {

    @Mock
    private FromProcessedDataDtoMapper fromProcessedDataDtoMapper;

    @Mock
    private ProcessedTransactionData processedTransactionData;

    @Mock
    private ProcessedDataDto processedDataDto;

    @InjectMocks
    private FromDataDtoMapper sut;

    @Before
    public void setup() {
        when(fromProcessedDataDtoMapper.map(processedDataDto)).thenReturn(processedTransactionData);
    }

    @Test
    public void map() {
        final DataDto input = new DataDto();
        input.setData(processedDataDto);

        final Data result = sut.map(input);

        assertThat(result.getData()).isEqualTo(processedTransactionData);
    }
}
