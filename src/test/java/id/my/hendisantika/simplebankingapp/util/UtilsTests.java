package id.my.hendisantika.simplebankingapp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/18/24
 * Time: 06:11
 * To change this template use File | Settings | File Templates.
 */
@ExtendWith(SpringExtension.class)
public class UtilsTests {
    @Test
    void isEmptyReturnTrueWhenInputNull() {
        Assertions.assertTrue(Utils.isEmpty(null));
    }

    @Test
    void isEmptyReturnTrueWhenInputListEmpty() {
        Assertions.assertTrue(Utils.isEmpty(List.of()));
    }

    @Test
    void isEmptyReturnFalseWhenInputListHasValue() {
        Assertions.assertFalse(Utils.isEmpty(List.of("Item")));
    }
}
