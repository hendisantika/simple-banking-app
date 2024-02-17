package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:16
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class BalanceServiceTests {

    @MockBean
    private BalanceMapper balanceMapper;

    private BalanceService balanceService;

}
