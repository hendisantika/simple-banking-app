package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import id.my.hendisantika.simplebankingapp.model.Balance;
import id.my.hendisantika.simplebankingapp.model.Transaction;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import id.my.hendisantika.simplebankingapp.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:13
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BalanceService {
    private final BalanceMapper balanceMapper;

    @Transactional
    public Balance updateAmountOfBalance(Transaction transaction) throws ApiException {
        List<Balance> balances = balanceMapper.findByAccountId(transaction.getAccountId());
        if (Utils.isEmpty(balances)) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "Account is missing");
        }
        Balance balance = balances
                .stream()
                .filter(b -> Objects.equals(b.getCurrency(), transaction.getCurrency()))
                .findFirst()
                .orElse(null);

        if (balance == null) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "Balance is missing");
        }

        BigDecimal calculatedAmount = balance.getAmount();
        if (Objects.equals(Direction.IN, transaction.getDirection())) {
            calculatedAmount = calculatedAmount.add(transaction.getAmount());
        } else {
            calculatedAmount = calculatedAmount.subtract(transaction.getAmount());
        }

        if (calculatedAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE,
                    "Insufficient funds");
        }

        balance.setAmount(calculatedAmount);
        balanceMapper.updateAmount(balance);

        return balance;
    }
}
