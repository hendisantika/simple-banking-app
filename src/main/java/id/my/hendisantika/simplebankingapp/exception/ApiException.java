package id.my.hendisantika.simplebankingapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:03
 * To change this template use File | Settings | File Templates.
 */
@Getter
public class ApiException extends Exception {
    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
