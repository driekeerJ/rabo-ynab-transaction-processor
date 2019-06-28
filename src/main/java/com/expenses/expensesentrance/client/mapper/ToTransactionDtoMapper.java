package com.expenses.expensesentrance.client.mapper;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.TransactionDto;
import com.expenses.expensesentrance.common.ContextMapper;
import com.expenses.expensesentrance.common.model.Transaction;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ToTransactionDtoMapper implements ContextMapper<String, Transaction, TransactionDto> {


    private static final int YNAB_MILIUNITS_MULTIPLIER = 1000;

    private static final String CLEARED_VALUE = "cleared";

    @Override
    public TransactionDto map(final String accountId, final Transaction input) {
        return TransactionDto.builder()
                .account_id(accountId)
                .amount(getYnabAmount(input.getAmount()))
                .date(input.getDate()
                        .toString())
                .import_id(createUuidFromTransaction(input))
                .memo(input.getDescription() + " " + input.getCounterPartyAccount())
                .cleared(CLEARED_VALUE)
                .payee_name(input.getCounterPartyName())
                .build();
    }

    UUID createUuidFromTransaction(final Transaction input) {
        final String transactionInfo =  getYnabAmount(input.getAmount()) + input.getDate()
                .toString() + input.getDescription() + input.getCounterPartyAccount();
        byte[] bytes = transactionInfo.getBytes(StandardCharsets.UTF_8);
        final UUID uuid = UUID.nameUUIDFromBytes(bytes);
        log.debug("UUID is geworden: " + uuid);
        return uuid;
    }

    private int getYnabAmount(final double input) {
        return (int) (input * YNAB_MILIUNITS_MULTIPLIER);
    }
}
