package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.service.AccountService;
import id.my.hendisantika.simplebankingapp.validator.AccountFormValidator;
import lombok.RequiredArgsConstructor;
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
}
