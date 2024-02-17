package id.my.hendisantika.simplebankingapp.validator;

import id.my.hendisantika.simplebankingapp.exception.ApiException;
import id.my.hendisantika.simplebankingapp.form.TransactionForm;
import id.my.hendisantika.simplebankingapp.model.enums.Currency;
import id.my.hendisantika.simplebankingapp.model.enums.Direction;
import id.my.hendisantika.simplebankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TransactionFormValidator implements Validator {

    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return TransactionForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransactionForm form = (TransactionForm) target;
        validateTransaction(form, errors);
        validateIfAccountExists(form, errors);
    }

    private void validateTransaction(TransactionForm form, Errors errors) {
        if (!Currency.currencySet().contains(form.getCurrency())) {
            errors.rejectValue("currency", "", "Invalid currency");
        }

        if (!Direction.directionSet().contains(form.getDirection())) {
            errors.rejectValue("direction", "", "Invalid direction");
        }
    }

    private void validateIfAccountExists(TransactionForm form, Errors errors) {
        try {
            accountService.getAccount(form.getAccountId());
        } catch (ApiException exception) {
            errors.rejectValue("accountId", "", "Account missing");
        }
    }
}
