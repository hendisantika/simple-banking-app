package id.my.hendisantika.simplebankingapp.util;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:06
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static String getErrorMessage(BindingResult result) {
        return result.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
    }
}
