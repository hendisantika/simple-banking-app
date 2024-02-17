package id.my.hendisantika.simplebankingapp.controller;

import id.my.hendisantika.simplebankingapp.dto.TransactionResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.form.TransactionForm;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import id.my.hendisantika.simplebankingapp.service.TransactionService;
import id.my.hendisantika.simplebankingapp.validator.TransactionFormValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

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

    @BeforeEach
    void setUp() {
        controller = new TransactionController(validator, transactionService);
        form = new TransactionForm();
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(validator).validate(Mockito.any(), Mockito.any());
        response = new TransactionResponse(0, 0,
                BigDecimal.ZERO, Currency.USD, Direction.IN, "", BigDecimal.ZERO);
        try {
            Mockito.when(transactionService.createTransaction(Mockito.any()))
                    .thenReturn(response);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void createTransactionWillReturnResponse() {
        TransactionResponse response = null;
        try {
            response = controller.createTransaction(form, bindingResult);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(response);
    }

    @Test
    void createTransactionWillThrowExceptionIfFormHasProblem() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            controller.createTransaction(form, bindingResult);
        });

        Assertions.assertNotNull(apiException);
    }
}
