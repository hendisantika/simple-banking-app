package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.TransactionResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.form.TransactionForm;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import id.my.hendisantika.simplebankingapp.service.TransactionService;
import id.my.hendisantika.simplebankingapp.util.Utils;
import id.my.hendisantika.simplebankingapp.validator.TransactionFormValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:17
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionFormValidator validator;
    private final TransactionService transactionService;

    @PostMapping("/api/transaction/create")
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionForm form,
                                                 BindingResult bindingResult)
            throws ApiException {
        log.info("TRX requested");
        validator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE,
                    Utils.getErrorMessage(bindingResult));
        }

        return transactionService.createTransaction(form.toModel());
    }

    @GetMapping("/api/transaction/{accountId}")
    public List<Transaction> getTransactions(@PathVariable("accountId") long accountId) throws ApiException {
        return transactionService.getTransactions(accountId);
    }
}
