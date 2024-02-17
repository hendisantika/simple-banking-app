package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.AccountResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.form.AccountForm;
import id.my.hendisantika.simplebankingapp.service.AccountService;
import id.my.hendisantika.simplebankingapp.util.Utils;
import id.my.hendisantika.simplebankingapp.validator.AccountFormValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:15
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountFormValidator validator;
    private final AccountService accountService;

    @PostMapping("/api/account/create")
    public AccountResponse createAccount(@Valid @RequestBody AccountForm form,
                                         BindingResult bindingResult)
            throws ApiException {

        validator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE,
                    Utils.getErrorMessage(bindingResult));
        }

        return accountService.createAccount(form.toModel());
    }

    @GetMapping("/api/account/{accountId}")
    public AccountResponse getAccount(@PathVariable("accountId") long accountId) throws ApiException {
        return accountService.getAccount(accountId);
    }
}
