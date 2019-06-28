package com.expenses.expensesentrance.core.translator;

import static java.util.Locale.GERMANY;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.common.Mapper;
import com.expenses.expensesentrance.common.model.Transaction;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class TransactionMapper implements Mapper<Map<String, String>, Transaction> {

    private static final String ACCOUNT_LABEL = "IBAN/BBAN";
    private static final String AMOUNT_LABEL = "Bedrag";
    private static final String COUNTERPARTY_ACCOUNT_LABEL = "Tegenrekening IBAN/BBAN";
    private static final String COUNTERPARTY_NAME_LABEL = "Naam tegenpartij";
    private static final String DESCRIPTION_LABEL = "Omschrijving-1";
    private static final String DATE_LABEL = "Datum";

    @Override
    public Transaction map(final Map<String, String> bankSpecificTransaction) {
        return Transaction.builder()
                .account(bankSpecificTransaction.get(ACCOUNT_LABEL))
                .amount(fetchDoubleAmount(bankSpecificTransaction.get(AMOUNT_LABEL)))
                .counterPartyAccount(bankSpecificTransaction.get(COUNTERPARTY_ACCOUNT_LABEL))
                .counterPartyName(bankSpecificTransaction.get(COUNTERPARTY_NAME_LABEL))
                .description(bankSpecificTransaction.get(DESCRIPTION_LABEL))
                .date(LocalDate.parse(bankSpecificTransaction.get(DATE_LABEL)))
                .build();
    }

    private double fetchDoubleAmount(final String doubleInString) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(GERMANY);
        format.applyPattern("+##0,00;-##0,00");

        double amount = 0;

        try {
            amount = format.parse(doubleInString).doubleValue();
        } catch (ParseException pe) {
            log.warn(pe.getMessage());
        }
        return amount;
    }
}
