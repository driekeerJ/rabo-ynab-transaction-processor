package com.expenses.expensesentrance.core.orchestrator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.Transaction;
import com.expenses.expensesentrance.core.translator.TranslatorService;
import com.expenses.expensesentrance.core.ynab.YnabService;

@RunWith(MockitoJUnitRunner.class)
public class OrchestratorServiceTest {

    @Mock
    private TranslatorService translatorService;

    @Mock
    private YnabService ynabService;

    @InjectMocks
    private OrchestratorService sut;

    @Mock
    private List<Transaction> transactions;

    @Mock
    private Data data;

    @Mock
    private MultipartFile multipartFile;

    @Test
    public void setup() {

        final String token = "token";

        final String budget = "budget";

        final String account = "account";

        when(translatorService.translate(any(MultipartFile.class))).thenReturn(transactions);
        when(ynabService.processTransactions(transactions, token, budget, account)).thenReturn(data);

        final Data result = sut.processTransactions(multipartFile, token, budget, account);
        assertThat(result).isEqualTo(data);
    }

}
