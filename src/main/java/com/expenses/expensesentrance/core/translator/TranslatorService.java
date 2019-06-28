package com.expenses.expensesentrance.core.translator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expenses.expensesentrance.common.model.Transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TranslatorService {

    private final FileMapper fileMapper;

    private final TransactionMapper transactionMapper;

    public List<Transaction> translate(final MultipartFile file) {
        List<Map<String, String>> bankSpecificTransactions = null;
        try {
            bankSpecificTransactions = fileMapper.mapFile(file);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return transactionMapper.map(bankSpecificTransactions);
    }
}
