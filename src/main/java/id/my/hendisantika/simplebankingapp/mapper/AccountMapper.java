package id.my.hendisantika.simplebankingapp.mapper;

import id.my.hendisantika.simplebankingapp.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:10
 * To change this template use File | Settings | File Templates.
 */
@Mapper
public interface AccountMapper {
    void insertAccount(Account account);

    Optional<Account> findById(@Param("id") long accountId);
}
