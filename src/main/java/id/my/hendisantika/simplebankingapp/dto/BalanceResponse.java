package id.my.hendisantika.simplebankingapp.dto;

import id.my.hendisantika.simplebankingapp.model.enums.Currency;
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
public class BalanceResponse {
    private Currency currency;
    private BigDecimal amount;
}
