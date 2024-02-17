package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.TransactionResponse;
import id.my.hendisantika.simplebankingapp.form.TransactionForm;
import id.my.hendisantika.simplebankingapp.service.TransactionService;
import id.my.hendisantika.simplebankingapp.validator.TransactionFormValidator;
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
 * Time: 06:22
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private TransactionFormValidator validator;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private BindingResult bindingResult;

    private TransactionController controller;
    private TransactionForm form;
    private TransactionResponse response;
}
