package com.expenses.expensesentrance.client.mapper;

import static com.expenses.expensesentrance.common.TransactionTestFactory.createTransaction;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.expensesentrance.client.model.TransactionDto;
import com.expenses.expensesentrance.client.model.TransactionsDto;
import com.expenses.expensesentrance.common.model.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class ToTransactionsDtoMapperTest {

    @Mock
    private ToTransactionDtoMapper toTransactionDtoMapper;

    @Mock
    private List<TransactionDto> transactionsDto;

    @InjectMocks
    private ToTransactionsDtoMapper sut;

    @Test
    public void map() {

        final String account = "ACCOUNT";

        final List<Transaction> transactions = singletonList(createTransaction());

        when(toTransactionDtoMapper.map(account, transactions)).thenReturn(transactionsDto);
        final TransactionsDto output = sut.map(account, transactions);

        assertThat(output.getTransactions()).isEqualTo(transactionsDto);
    }
}
