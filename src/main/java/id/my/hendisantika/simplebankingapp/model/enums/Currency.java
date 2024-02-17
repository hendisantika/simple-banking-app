package id.my.hendisantika.simplebankingapp.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:00
 * To change this template use File | Settings | File Templates.
 */
@Getter
public enum Currency {
    IDR,
    EUR,
    SEK,
    GBP,
    USD;

    public static Set<Currency> currencySet() {
        return Arrays.stream(Currency.values())
                .collect(Collectors.toSet());
    }
}
