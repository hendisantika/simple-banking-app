package id.my.hendisantika.simplebankingapp.model;

import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 06:59
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Transaction {
    private long id;
    private long accountId;
    private Currency currency;
    private Direction direction;
    private String description;
    private BigDecimal amount;
}
