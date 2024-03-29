package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.config.MessagePublisher;
import id.my.hendisantika.simplebankingapp.dto.AccountResponse;
import id.my.hendisantika.simplebankingapp.dto.BalanceResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.AccountMapper;
import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import id.my.hendisantika.simplebankingapp.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:11
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final MessagePublisher publisher;
    private final AccountMapper accountMapper;
    private final BalanceMapper balanceMapper;

    @Transactional
    public AccountResponse createAccount(Account account) {
        accountMapper.insertAccount(account);
        account.getBalances().forEach(balance -> {
            balance.setAccountId(account.getId());
            balance.setAmount(BigDecimal.ZERO);
        });

        balanceMapper.insertBalances(account.getBalances());
        publisher.publishAccountDetails(account);

        return getAccountResponse(account);
    }

    public AccountResponse getAccount(long accountId) throws ApiException {
        Account account = accountMapper.findById(accountId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account not found"));
        return getAccountResponse(account);
    }

    private AccountResponse getAccountResponse(Account account) {
        return new AccountResponse(account.getId(), account.getCustomerId(),
                account.getBalances()
                        .stream()
                        .map(balance -> new BalanceResponse(balance.getCurrency(), balance.getAmount()))
                        .collect(Collectors.toList()));
    }
}
