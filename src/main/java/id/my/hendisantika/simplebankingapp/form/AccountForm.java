package id.my.hendisantika.simplebankingapp.form;

import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

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
}
