package id.my.hendisantika.simplebankingapp;

import id.my.hendisantika.simplebankingapp.model.Account;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:09
 * To change this template use File | Settings | File Templates.
 */
public class UnitTestHelper {
    public static Account getAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId("customer");
        account.setCountry("ID");
        account.setBalances(Collections.emptyList());
        return account;
    }

    public static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAccountId(1);
        transaction.setAmount(BigDecimal.ZERO);
        transaction.setDirection(Direction.IN);
        transaction.setCurrency(Currency.USD);
        transaction.setDescription("");
        return transaction;
    }

    public static Balance getBalance() {
        Balance balance = new Balance();
        balance.setCurrency(Currency.USD);
        balance.setAmount(BigDecimal.valueOf(5));
        balance.setAccountId(5);
        return balance;
    }
}
