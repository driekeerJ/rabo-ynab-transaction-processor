package com.expenses.expensesentrance.core.translator;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.expenses.expensesentrance.common.model.Transaction;

public class TransactionMapperTest {

    private static final String ACCOUNT = "account";

    private static final String COUNTERPARTY_ACCOUNT = "CPAL";

    private static final String COUNTERPARTY_NAME = "CPNL";

    private final TransactionMapper sut = new TransactionMapper();

    private static final List<Map<String, String>> bankSpecificTransactions = new ArrayList<>();

    private static final String ACCOUNT_LABEL = "IBAN/BBAN";

    private static final String AMOUNT_LABEL = "Bedrag";

    private static final String COUNTERPARTY_ACCOUNT_LABEL = "Tegenrekening IBAN/BBAN";

    private static final String COUNTERPARTY_NAME_LABEL = "Naam tegenpartij";

    private static final String DESCRIPTION_LABEL = "Omschrijving-1";

    private static final String DATE_LABEL = "Datum";


    @Before
    public void setUp() {
        bankSpecificTransactions.add(createTransactionMap("descr 1"));
        bankSpecificTransactions.add(createTransactionMap("descr 2"));
    }


    @Test
    public void map() {
        List<Transaction> transactions = sut.map(bankSpecificTransactions);
        assertThat(transactions).hasSize(2);

        Transaction transaction = transactions.get(0);
        assertThat(transaction.getAccount()).isEqualTo(ACCOUNT);
        assertThat(transaction.getAmount()).isEqualTo(123456.0);
        assertThat(transaction.getCounterPartyAccount()).isEqualTo(COUNTERPARTY_ACCOUNT);
        assertThat(transaction.getCounterPartyName()).isEqualTo(COUNTERPARTY_NAME);
        assertThat(transaction.getDate()).isEqualTo(LocalDate.of(2019,3,1));
        assertThat(transaction.getDescription()).isEqualTo("descr 1");
        assertThat(transactions.get(1).getDescription()).isEqualTo("descr 2");
    }

    private Map<String, String> createTransactionMap(final String description) {
        Map<String, String> map = new HashMap<>();
        map.put(ACCOUNT_LABEL, ACCOUNT);
        map.put(AMOUNT_LABEL, "+123456,00");
        map.put(COUNTERPARTY_ACCOUNT_LABEL, COUNTERPARTY_ACCOUNT);
        map.put(COUNTERPARTY_NAME_LABEL, COUNTERPARTY_NAME);
        map.put(DESCRIPTION_LABEL, description);
        map.put(DATE_LABEL, "2019-03-01");
        return map;
    }
}
