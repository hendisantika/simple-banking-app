package id.my.hendisantika.simplebankingapp.config;

import id.my.hendisantika.simplebankingapp.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:12
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class MessagePublisherTests {
    private final String queueName = "accountQueue";
    @MockBean
    private RabbitTemplate rabbitTemplate;
    @MockBean
    private Queue queue;
    private MessagePublisher messagePublisher;

    @BeforeEach
    void setUp() {
        messagePublisher = new MessagePublisher(rabbitTemplate, queue);
        messagePublisher.publishAccountDetails(new Account());
    }
}
