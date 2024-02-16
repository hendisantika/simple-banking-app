package id.my.hendisantika.simplebankingapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@EqualsAndHashCode
public class Balance {
    private long id;
    private long accountId;
    private BigDecimal amount;
    private Currency currency;
}
