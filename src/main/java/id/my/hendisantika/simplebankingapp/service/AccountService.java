package id.my.hendisantika.simplebankingapp.service;

import id.my.hendisantika.simplebankingapp.config.MessagePublisher;
import id.my.hendisantika.simplebankingapp.mapper.AccountMapper;
import id.my.hendisantika.simplebankingapp.mapper.BalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:11
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final MessagePublisher publisher;
    private final AccountMapper accountMapper;
    private final BalanceMapper balanceMapper;
}
