package id.my.hendisantika.simplebankingapp.validator;

import id.my.hendisantika.simplebankingapp.form.AccountForm;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-banking-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2/17/24
 * Time: 07:07
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AccountFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AccountForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm form = (AccountForm) target;
        validateCurrencies(form, errors);
    }

    private void validateCurrencies(AccountForm form, Errors errors) {
        if (form.getCurrencies() != null &&
                !form.getCurrencies().isEmpty() &&
                !Currency.currencySet().containsAll(form.getCurrencies())) {
            errors.rejectValue("currencies", "", "Invalid currency");
        }
    }
}
