package com.expenses.expensesentrance.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.ProcessedTransaction;
import com.expenses.expensesentrance.common.model.ProcessedTransactionData;
import com.expenses.expensesentrance.core.orchestrator.OrchestratorService;

@RunWith(MockitoJUnitRunner.class)
public class Rabo2YnabControllerTest {

    @Mock
    private OrchestratorService orchestratorService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private Rabo2YnabController sut;

    private static final MultipartFile MULTIPART_FILE = new MockMultipartFile("Naam", "INHOUD".getBytes());

    private static final String TOKEN = "dx5UtEXAvAZn372PK383q5hEjLEJrCmayRLZLJf3KkXSX3hyvxqJHjKUSp5sh3uP";

    private static final String ACCOUNT = "ACCOUNT";

    private static final String BUDGET = "BUDGET";

    private static final String REDIRECT = "redirect:/";

    @Before
    public void setup() {
        final Data data = Data.builder()
                .data(ProcessedTransactionData.builder()
                        .transactions(Collections.singletonList(ProcessedTransaction.builder()
                                .build()))
                        .build())
                .build();

        when(orchestratorService.processTransactions(MULTIPART_FILE, TOKEN, BUDGET, ACCOUNT)).thenReturn(data);
    }

    @Test
    public void getIn() {
        assertThat(sut.getIn()).isEqualTo("index");
    }

    @Test
    public void whenTokenNotFilled_thenReturnRedirect() {
        final String output = sut.processTransactionsRequest(MULTIPART_FILE, "", BUDGET, ACCOUNT, redirectAttributes);
        assertThat(output).isEqualTo(REDIRECT);
    }

    @Test
    public void whenBudgetNotFilled_thenReturnRedirect() {
        final String output = sut.processTransactionsRequest(MULTIPART_FILE, TOKEN, "", ACCOUNT, redirectAttributes);
        assertThat(output).isEqualTo(REDIRECT);
    }

    @Test
    public void whenAccountNotFilled_thenReturnRedirect() {
        final String output = sut.processTransactionsRequest(MULTIPART_FILE, TOKEN, BUDGET, "", redirectAttributes);
        assertThat(output).isEqualTo(REDIRECT);
    }

    @Test
    public void whenTokenNot64Length_thenReturnRedirect() {
        final String output = sut.processTransactionsRequest(MULTIPART_FILE, TOKEN + "1", BUDGET, ACCOUNT, redirectAttributes);
        assertThat(output).isEqualTo(REDIRECT);
    }

    @Test
    public void whenAllIsFilledCorrect_thenReturnRedirect() {
        final String output = sut.processTransactionsRequest(MULTIPART_FILE, TOKEN, BUDGET, ACCOUNT, redirectAttributes);
        assertThat(output).isEqualTo(REDIRECT);
    }
}
