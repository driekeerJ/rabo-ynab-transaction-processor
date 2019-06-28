package com.expenses.expensesentrance.core.ynab;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.expensesentrance.client.YnabClient;
import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class YnabServiceTest {

    @Mock
    private YnabClient ynabClient;

    @InjectMocks
    private YnabService sut;

    @Mock
    private Data data;

    @Test
    public void processTransactions() {

        when(ynabClient.processTransactions(anyList(), anyString(), anyString(), anyString())).thenReturn(data);

        final Data result = sut.processTransactions(Collections.singletonList(Transaction.builder().build()), "TOKEN", "BUDGET", "ACCOUNT");

        assertThat(result).isEqualTo(data);

    }
}
