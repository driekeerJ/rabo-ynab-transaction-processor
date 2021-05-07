package com.expenses.expensesentrance.core.orchestrator;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.common.model.ProcessedTransaction;
import com.expenses.expensesentrance.common.model.ProcessedTransactionData;
import com.expenses.expensesentrance.common.model.Transaction;
import com.expenses.expensesentrance.core.translator.TranslatorService;
import com.expenses.expensesentrance.core.ynab.YnabService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrchestratorService {

    private final TranslatorService translatorService;

    private final YnabService ynabService;

    public Data processTransactions(final MultipartFile file, final String token, final String budget, final String account) {

        final List<Transaction> transactions = translatorService.translate(file);

        final Data data = ynabService.processTransactions(transactions, token, budget, account);

        final ProcessedTransactionData processedTransactionData = data.getData();

        return Data.builder()
                .numberOfRecordsReceived(transactions.size())
                .numberOfRecordsDuplicated(processedTransactionData.getDuplicatedTransactionIds().size())
                .numberOfRecordsMatched(countNumberOfMatchedRecords(processedTransactionData))
                .numberOfRecordsProcessed(processedTransactionData.getTransactions().size())
                .build();
    }

    private long countNumberOfMatchedRecords(final ProcessedTransactionData processedTransactionData) {
        return processedTransactionData.getTransactions()
                .stream()
                .map(ProcessedTransaction::getMatchedTransactionId)
                .filter(Objects::nonNull)
                .count();
    }

}
