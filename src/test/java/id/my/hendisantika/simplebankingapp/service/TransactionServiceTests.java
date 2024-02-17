package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.UnitTestHelper;
import id.my.hendisantika.simplebankingapp.config.MessagePublisher;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.AccountMapper;
import id.my.hendisantika.simplebankingapp.mapper.TransactionMapper;
import id.my.hendisantika.simplebankingapp.model.Account;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:17
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class TransactionServiceTests {
    @MockBean
    private MessagePublisher publisher;
    @MockBean
    private BalanceService balanceService;
    @MockBean
    private AccountMapper accountMapper;
    @MockBean
    private TransactionMapper transactionMapper;

    private TransactionService transactionService;

    private Account account;
    private Balance balance;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(publisher, balanceService, accountMapper, transactionMapper);
        account = UnitTestHelper.getAccount();
        balance = UnitTestHelper.getBalance();
        transaction = UnitTestHelper.getTransaction();
        try {
            Mockito.when(balanceService.updateAmountOfBalance(Mockito.any())).thenReturn(balance);
            Mockito.when(accountMapper.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
        } catch (ApiException exception) {
            exception.printStackTrace();
        }
    }
}