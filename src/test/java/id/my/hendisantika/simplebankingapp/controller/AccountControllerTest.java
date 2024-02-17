package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.AccountResponse;
import id.my.hendisantika.simplebankingapp.form.AccountForm;
import id.my.hendisantika.simplebankingapp.service.AccountService;
import id.my.hendisantika.simplebankingapp.validator.AccountFormValidator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:20
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
class AccountControllerTests {
    @MockBean
    private AccountFormValidator validator;
    @MockBean
    private AccountService accountService;
    @MockBean
    private BindingResult bindingResult;

    private AccountController controller;
    private AccountForm form;
    private AccountResponse response;

}
