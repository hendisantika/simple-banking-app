package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.config.MessagePublisher;
import id.my.hendisantika.simplebankingapp.dto.TransactionResponse;
import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.AccountMapper;
import id.my.hendisantika.simplebankingapp.mapper.TransactionMapper;
import id.my.hendisantika.simplebankingapp.model.Account;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {
    private final MessagePublisher publisher;
    private final BalanceService balanceService;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    @Transactional
    public TransactionResponse createTransaction(Transaction transaction) throws ApiException {
        Balance balance = balanceService.updateAmountOfBalance(transaction);

        try {
            transactionMapper.insertTransaction(transaction);
        } catch (Exception exception) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getMessage());
        }

        Account account = accountMapper.findById(transaction.getAccountId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account missing"));

        publisher.publishAccountDetails(account);

        return getTransactionResponse(transaction, balance.getAmount());
    }
}
