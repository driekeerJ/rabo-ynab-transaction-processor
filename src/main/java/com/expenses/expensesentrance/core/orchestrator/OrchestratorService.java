package com.expenses.expensesentrance.core.orchestrator;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expenses.expensesentrance.common.model.Data;
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

        return ynabService.processTransactions(transactions, token, budget, account);
    }

}
