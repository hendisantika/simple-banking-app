package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.UnitTestHelper;
import id.my.hendisantika.simplebankingapp.config.MessagePublisher;
import id.my.hendisantika.simplebankingapp.dto.AccountResponse;
import id.my.hendisantika.simplebankingapp.dto.BalanceResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.AccountMapper;
import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import id.my.hendisantika.simplebankingapp.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:13
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class AccountServiceTests {
    @MockBean
    private MessagePublisher publisher;

    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private BalanceMapper balanceMapper;

    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(publisher, accountMapper, balanceMapper);
        account = UnitTestHelper.getAccount();
    }

    @Test
    void createAccountWillCallInsertAccountOfMapper() {
        try {
            accountService.createAccount(account);
            Mockito.verify(accountMapper, Mockito.times(1)).insertAccount(account);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void createAccountWillCallInsertBalanceOnBalanceMapper() {
        try {
            accountService.createAccount(account);
            Mockito.verify(balanceMapper, Mockito.times(1)).insertBalances(account.getBalances());
        } catch (ApiException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void createAccountWillPublishMessage() {
        try {
            accountService.createAccount(account);
            Mockito.verify(publisher, Mockito.times(1)).publishAccountDetails(account);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void getAccountThroughExceptionWhenAccountNotFound() {
        Mockito.when(accountMapper.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            accountService.getAccount(1L);
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, apiException.getStatus());
    }

    @Test
    void getAccountReturnResponseWhenAccountIsFound() {
        Mockito.when(accountMapper.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
        AccountResponse accountResponse = null;
        try {
            accountResponse = accountService.getAccount(1L);
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(accountResponse);
        Assertions.assertEquals(accountResponse.getCustomerId(), account.getCustomerId());
    }

    @Test
    void getAccountReturnBalanceAlsoWhenAccountIsFound() {
        account.setBalances(List.of(UnitTestHelper.getBalance()));
        Mockito.when(accountMapper.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
        List<BalanceResponse> balanceResponses = null;
        try {
            AccountResponse accountResponse = accountService.getAccount(1L);
            balanceResponses = accountResponse.getBalances();
        } catch (ApiException exception) {
            exception.printStackTrace();
        }

        Assertions.assertNotNull(balanceResponses);
        Assertions.assertEquals(1, balanceResponses.size());
    }
}
