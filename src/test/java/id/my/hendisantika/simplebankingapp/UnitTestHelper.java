package id.my.hendisantika.simplebankingapp;

import id.my.hendisantika.simplebankingapp.model.Account;

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
}
