package com.expenses.expensesentrance.client.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;

import com.expenses.expensesentrance.client.model.TransactionDto;
import com.expenses.expensesentrance.common.model.Transaction;

public class ToTransactionDtoMapperTest {

    private static final String COUNTER_PARTY_ACCOUNT = "CPA";

    private static final String COUNTER_PARTY_NAME = "NAME";

    private static final LocalDate LOCAL_DATE = LocalDate.of(2018, 12, 20);

    private static final String DESCRIPTION = "DESCR";

    private static final String accountId = "ACCOUNT_ID";

    private final ToTransactionDtoMapper sut = new ToTransactionDtoMapper();

    @Test
    public void map() {

        final TransactionDto result = sut.map(accountId, createTransaction(200.68));

        assertThat(result.getAccount_id()).isEqualTo(accountId);
        assertThat(result.getPayee_name()).isEqualTo(COUNTER_PARTY_NAME);
        assertThat(result.getImport_id()).isNotNull();
        assertThat(result.getDate()).isEqualTo(LOCAL_DATE.toString());
        assertThat(result.getAmount()).isEqualTo(200680);
        assertThat(result.getMemo()).isEqualTo(DESCRIPTION + " " + COUNTER_PARTY_ACCOUNT);
        assertThat(result.getCleared()).isEqualTo("cleared");
    }

    @Test
    public void whenTransactionIsFilled_thenUniqueUUIDIsCreated() {
        final Transaction transaction1Equal = createTransaction(20);
        final Transaction transaction2Equal = createTransaction(20);
        final Transaction transaction3NotEqual = createTransaction(21);

        final UUID onTrans1 = sut.createUuidFromTransaction(transaction1Equal);
        final UUID onTrans2 = sut.createUuidFromTransaction(transaction2Equal);
        final UUID onTrans3 = sut.createUuidFromTransaction(transaction3NotEqual);

        assertThat(onTrans1).isEqualTo(onTrans2);
        assertThat(onTrans2).isNotEqualTo(onTrans3);
    }

    private Transaction createTransaction(final double amount) {
        return Transaction.builder()
                .account(accountId)
                .amount(amount)
                .counterPartyAccount(COUNTER_PARTY_ACCOUNT)
                .counterPartyName(COUNTER_PARTY_NAME)
                .date(LOCAL_DATE)
                .description(DESCRIPTION)
                .build();
    }
}
