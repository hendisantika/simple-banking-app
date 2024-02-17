package id.my.hendisantika.simplebankingapp.dto;

import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:02
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class TransactionResponse {
    private long accountId;
    private long transactionId;
    private BigDecimal amount;
    private Currency currency;
    private Direction direction;
    private String description;
    private BigDecimal balanceAfterTransaction;
}
