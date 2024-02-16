package id.my.hendisantika.simplebankingapp.model;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 06:58
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Account {
    private long id;
    private String customerId;
    private String country;
    private List<Balance> balances;
}
