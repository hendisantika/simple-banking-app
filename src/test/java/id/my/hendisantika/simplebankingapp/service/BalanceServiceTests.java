package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.UnitTestHelper;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:16
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class BalanceServiceTests {

    @MockBean
    private BalanceMapper balanceMapper;

    private BalanceService balanceService;

    @BeforeEach
    void setUp() {
        balanceService = new BalanceService(balanceMapper);
        Mockito.when(balanceMapper.findByAccountId(Mockito.anyLong())).thenReturn(balances());
    }

    private List<Balance> balances() {
        Balance balance1 = new Balance();
        balance1.setCurrency(Currency.USD);
        balance1.setAmount(BigDecimal.ZERO);
        balance1.setAccountId(5);

        Balance balance2 = new Balance();
        balance2.setCurrency(Currency.EUR);
        balance2.setAmount(BigDecimal.ZERO);
        balance2.setAccountId(5);

        return Arrays.asList(balance1, balance2);
    }

    @Test
    void updateBalanceWillThrowExceptionWhenBalanceListEmpty() {
        Mockito.when(balanceMapper.findByAccountId(Mockito.anyLong())).thenReturn(Collections.emptyList());
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            balanceService.updateAmountOfBalance(UnitTestHelper.getTransaction());
        });

        Assertions.assertNotNull(apiException);
    }

    @Test
    void updateBalanceWilThrowExceptionIfBalanceNotFoundForCurrency() {
        Transaction transaction = UnitTestHelper.getTransaction();
        transaction.setCurrency(Currency.SEK);

        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            balanceService.updateAmountOfBalance(transaction);
        });

        Assertions.assertNotNull(apiException);
    }

    @Test
    void updateBalanceWillAddAmountForInDirection() {
        BigDecimal targetAmount = BigDecimal.valueOf(10);
        Transaction transaction = UnitTestHelper.getTransaction();
        transaction.setAmount(targetAmount);

        Balance balance = null;
        try {
            balance = balanceService.updateAmountOfBalance(transaction);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(balance);
        Assertions.assertEquals(0, balance.getAmount().compareTo(targetAmount));
    }

    @Test
    void updateBalanceThrowExceptionOutDirectionWhenAmountBecomeNegative() {
        Transaction transaction = UnitTestHelper.getTransaction();
        transaction.setDirection(Direction.OUT);
        transaction.setAmount(BigDecimal.valueOf(10));

        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            balanceService.updateAmountOfBalance(transaction);
        });

        Assertions.assertNotNull(apiException);
    }
}
