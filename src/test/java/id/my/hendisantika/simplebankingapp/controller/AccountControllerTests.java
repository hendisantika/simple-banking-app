package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.AccountResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.form.AccountForm;
import id.my.hendisantika.simplebankingapp.service.AccountService;
import id.my.hendisantika.simplebankingapp.validator.AccountFormValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

    @BeforeEach
    void setUp() throws ApiException {
        controller = new AccountController(validator, accountService);
        form = new AccountForm();
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(validator).validate(Mockito.any(), Mockito.any());
        response = new AccountResponse(0, null, null);
        Mockito.when(accountService.createAccount(Mockito.any()))
                .thenReturn(response);
    }

    @Test
    void createAccountWillReturnResponse() {
        AccountResponse response = null;
        try {
            response = controller.createAccount(form, bindingResult);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(response);
    }

    @Test
    void createAccountWillThrowExceptionIfFormHasProblem() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            controller.createAccount(form, bindingResult);
        });

        Assertions.assertNotNull(apiException);
    }

    @Test
    void getAccountWillReturnResponse() {
        AccountResponse accountResponse = null;

        try {
            Mockito.when(accountService.getAccount(Mockito.anyLong())).thenReturn(response);
            accountResponse = controller.getAccount(1);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(accountResponse);
    }
}
