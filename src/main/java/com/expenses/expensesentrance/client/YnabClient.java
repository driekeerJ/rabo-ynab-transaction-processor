package com.expenses.expensesentrance.client;

import static java.util.Objects.requireNonNull;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.expenses.expensesentrance.client.mapper.FromDataDtoMapper;
import com.expenses.expensesentrance.client.mapper.ToTransactionsDtoMapper;
import com.expenses.expensesentrance.client.model.DataDto;
import com.expenses.expensesentrance.client.model.ProcessedTransactionDto;
import com.expenses.expensesentrance.client.model.TransactionsDto;
import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.Transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class YnabClient {

    private final FromDataDtoMapper fromDataDtoMapper;

    private final ToTransactionsDtoMapper toTransactionsDtoMapper;

    private final RestTemplate restTemplate;

    public Data processTransactions(final List<Transaction> transactions, final String token, final String budget, final String account) {
        final String ynabUrl = "https://api.youneedabudget.com/v1/budgets/" + budget + "/transactions";

        final TransactionsDto request = toTransactionsDtoMapper.map(account, transactions);

        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        final HttpEntity<TransactionsDto> entity = new HttpEntity<>(request, headers);

        final DataDto dataDto = restTemplate.postForObject(ynabUrl, entity, DataDto.class);
        System.out.printf("Send %d number of records to YNAB", entity.getBody().getTransactions().size());
        final List<ProcessedTransactionDto> processedTranctions = dataDto.getData()
                .getTransactions();
        System.out.printf("Number of processed transactions: %d", processedTranctions.size());
        System.out.printf("Number of matched transactions: %d", processedTranctions.stream().filter(processedTransactionDto -> processedTransactionDto.getMatched_transaction_id() != null).count());
        System.out.printf("Number of duplicated transactions: %d", dataDto.getData().getDuplicate_import_ids().size());
        return fromDataDtoMapper.map(requireNonNull(dataDto));
    }
}
