package id.my.hendisantika.simplebankingapp.form;

import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:05
 * To change this template use File | Settings | File Templates.
 */
@Data
public class TransactionForm {
    @Min(value = 1, message = "Account missing")
    private long accountId;

    @Min(value = 1, message = "Invalid amount")
    private BigDecimal amount;

    @NotNull(message = "Currency is not provided")
    private Currency currency;

    @NotNull(message = "Direction is not provided")
    private Direction direction;

    @NotEmpty(message = "Description missing")
    private String description;
}
