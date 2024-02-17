package id.my.hendisantika.simplebankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:01
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class AccountResponse {
    private long accountId;
    private String customerId;
    private List<BalanceResponse> balances;
}
