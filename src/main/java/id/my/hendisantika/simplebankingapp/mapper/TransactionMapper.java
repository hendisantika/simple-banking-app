package id.my.hendisantika.simplebankingapp.mapper;

import id.my.hendisantika.simplebankingapp.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
@Mapper
public interface TransactionMapper {
    void insertTransaction(Transaction transaction);

    List<Transaction> findByAccountId(@Param("accountId") long accountId);
}
