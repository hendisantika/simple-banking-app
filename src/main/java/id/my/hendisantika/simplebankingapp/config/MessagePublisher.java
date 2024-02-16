package id.my.hendisantika.simplebankingapp.config;

import id.my.hendisantika.simplebankingapp.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 06:56
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class MessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void publishAccountDetails(Account account) {
        try {
            rabbitTemplate.convertAndSend(queue.getName(), account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
