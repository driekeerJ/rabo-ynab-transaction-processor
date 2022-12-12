package com.expenses.expensesentrance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.expenses.expensesentrance.common.model.Data;
import com.expenses.expensesentrance.core.orchestrator.OrchestratorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class Rabo2YnabController {

    private final OrchestratorService orchestratorService;

    private static final int EXPECTED_TOKEN_SIZE = 64;

    private static final String FLASH_ATTRIBUTE_LABEL = "message";

    private static final String REDIRECT = "redirect:/";


    @GetMapping("/")
    public String getIn() {
        return "index";
    }

    @PostMapping("/")
    public String processTransactionsRequest(@RequestParam("file") MultipartFile file, @RequestParam("token") String token, @RequestParam("budget") String budget,
            @RequestParam("account") String account, final RedirectAttributes redirectAttributes) {

        if (hasInvalidInput(token, budget, account)) {
            redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_LABEL, "You didn't fill in the token, budget or account. Please fill in the attributes and try again");
            log.warn("No token filled");
            return REDIRECT;
        }

        if (token.length() != EXPECTED_TOKEN_SIZE) {
            redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_LABEL, "Your token didn't match the criteria of a token. Please check your token and retry");
            log.warn("Token not correctly filled. Token parsed: " + token);
            return REDIRECT;
        }

        log.info("Processing for budget: {} account: {}", budget, account);
        final Data result = orchestratorService.processTransactions(file, token, budget, account);
        redirectAttributes.addFlashAttribute(FLASH_ATTRIBUTE_LABEL, "You have successfully uploaded the transaction file. In total " + result.getData()
                .getTransactions()
                .size() + " transactions are processed");
        log.info("File is processed");
        return REDIRECT;
    }

    private boolean hasInvalidInput(final String token, final String budget, final String account) {
        return isNotNullOrEmpty(token) || isNotNullOrEmpty(budget) || isNotNullOrEmpty(account);
    }

    private boolean isNotNullOrEmpty(final String string) {
        return string == null || string.equals("");
    }
}
