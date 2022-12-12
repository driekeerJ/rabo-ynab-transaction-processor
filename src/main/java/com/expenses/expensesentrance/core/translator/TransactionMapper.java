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

    private static final String AMOUNT_LABEL = "Bedrag";
    private static final String DATE_LABEL = "Datum";
    private static String accountLabel = "IBAN/BBAN";
    private static String counterpartyAcountLabel = "Tegenrekening IBAN/BBAN";
    private static String counterPartyNameLabel = "Naam tegenpartij";
    private static String descriptionLabel = "Omschrijving-1";

    @Override
    public Transaction map(final Map<String, String> bankSpecificTransaction) {

        if (isCreditCard(bankSpecificTransaction)) {
            accountLabel = "Productnaam";
            counterpartyAcountLabel = "Omschrijving";
            counterPartyNameLabel = "Omschrijving";
            descriptionLabel = "Omschrijving";
        }

        return Transaction.builder()
                .account(bankSpecificTransaction.get(accountLabel))
                .amount(fetchDoubleAmount(bankSpecificTransaction.get(AMOUNT_LABEL)))
                .counterPartyAccount(bankSpecificTransaction.get(counterpartyAcountLabel))
                .counterPartyName(bankSpecificTransaction.get(counterPartyNameLabel))
                .description(bankSpecificTransaction.get(descriptionLabel))
                .date(LocalDate.parse(bankSpecificTransaction.get(DATE_LABEL)))
                .build();
    }

    private boolean isCreditCard(final Map<String, String> bankSpecificTransaction) {
        return bankSpecificTransaction.get("Productnaam").equals("RaboCard Mastercard");
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
