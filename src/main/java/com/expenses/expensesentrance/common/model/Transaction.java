package com.expenses.expensesentrance.common.model;

import static com.expenses.expensesentrance.common.model.ColumnType.ACCOUNT_LABEL;
import static com.expenses.expensesentrance.common.model.ColumnType.AMOUNT_LABEL;
import static com.expenses.expensesentrance.common.model.ColumnType.COUNTERPARTY_ACCOUNT_LABEL;
import static com.expenses.expensesentrance.common.model.ColumnType.COUNTERPARTY_NAME_LABEL;
import static com.expenses.expensesentrance.common.model.ColumnType.DATE_LABEL;
import static com.expenses.expensesentrance.common.model.ColumnType.DESCRIPTION_LABEL;
import static java.util.Locale.GERMANY;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class Transaction {
    private static final String UNIQUE_RABOBANK_COLUMN_NAME = "IBAN/BBAN";

    private final String account;

    private final LocalDate date;

    private final double amount;

    private final String counterPartyAccount;

    private final String counterPartyName;

    private final String description;

    public Transaction(final Map<String, String> transactionMap) {
        final Map<ColumnType, String> columnNames = getColumnNames(transactionMap);
        assert columnNames != null;
        this.account = transactionMap.get(columnNames.get(ACCOUNT_LABEL));
        this.date = LocalDate.parse(transactionMap.get(columnNames.get(DATE_LABEL)));
        this.amount = fetchDoubleAmount(transactionMap.get(columnNames.get(AMOUNT_LABEL)));
        this.counterPartyAccount = transactionMap.get(columnNames.get(COUNTERPARTY_ACCOUNT_LABEL));
        this.counterPartyName = transactionMap.get(columnNames.get(COUNTERPARTY_NAME_LABEL));
        this.description = transactionMap.get(columnNames.get(DESCRIPTION_LABEL));
    }

    private Map<ColumnType, String> getColumnNames(final Map<String, String> transactionMap) {
        if (isRabobankTransaction(transactionMap)) {
            return buildRabobankColumnNameMap();
        }
        return buildRabobankCreditCardColumnNameMap();
    }

    private Map<ColumnType, String> buildRabobankCreditCardColumnNameMap() {
        final String description = "Omschrijving";
        return Map.of(
                ACCOUNT_LABEL,"Creditcard Nummer",
                AMOUNT_LABEL, "Bedrag",
                COUNTERPARTY_ACCOUNT_LABEL, description,
                COUNTERPARTY_NAME_LABEL, description,
                DESCRIPTION_LABEL, description,
                DATE_LABEL, "Datum"
        );
    }

    private boolean isRabobankTransaction(final Map<String, String> transactionMap) {
        return transactionMap.containsKey(UNIQUE_RABOBANK_COLUMN_NAME);
    }

    private Map<ColumnType, String> buildRabobankColumnNameMap() {
        return Map.of(
                ACCOUNT_LABEL,"IBAN/BBAN",
                AMOUNT_LABEL, "Bedrag",
                COUNTERPARTY_ACCOUNT_LABEL, "Tegenrekening IBAN/BBAN",
                COUNTERPARTY_NAME_LABEL, "Naam tegenpartij",
                DESCRIPTION_LABEL, "Omschrijving-1",
                DATE_LABEL, "Datum"
        );
    }

    private double fetchDoubleAmount(final String doubleInString) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(GERMANY);
        format.applyPattern("+##0,00;-##0,00");

        double amount = 0;

        try {
            amount = format.parse(doubleInString)
                    .doubleValue();
        } catch (ParseException pe) {
            log.warn(pe.getMessage());
        }
        return amount;
    }

}
