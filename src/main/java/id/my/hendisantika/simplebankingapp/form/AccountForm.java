package id.my.hendisantika.simplebankingapp.form;

import id.my.hendisantika.simplebankingapp.model.Account;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.util.Utils;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:04
 * To change this template use File | Settings | File Templates.
 */
@Data
public class AccountForm {
    @NotEmpty(message = "Customer ID is not provided")
    String customerId;
    @NotEmpty(message = "Country is not provided")
    String country;
    @NotEmpty(message = "No currency is provided")
    List<Currency> currencies;

    public Account toModel() {
        Account account = new Account();
        account.setCountry(getCountry());
        account.setCustomerId(getCustomerId());
        account.setBalances(populateBalances());
        return account;
    }

    private List<Balance> populateBalances() {
        return Utils.isEmpty(getCurrencies()) ? Collections.emptyList() :
                getCurrencies()
                        .stream()
                        .map(currency -> {
                            Balance b = new Balance();
                            b.setCurrency(currency);
                            return b;
                        })
                        .collect(Collectors.toList());
    }
}
