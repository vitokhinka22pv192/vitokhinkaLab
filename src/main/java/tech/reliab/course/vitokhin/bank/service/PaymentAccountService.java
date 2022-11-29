package tech.reliab.course.vitokhin.bank.service;

import tech.reliab.course.vitokhin.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhin.bank.entity.User;

import java.util.List;


public interface PaymentAccountService {
    /**
     *
     * @param user - клиент
     * @param bankName - имя банка
     * @return - возвращает созданный объект платежный счет
     */
    PaymentAccount create(User user, String bankName);
    PaymentAccount read();
    void update(PaymentAccount paymentAccount);
    void delete(PaymentAccount paymentAccount);
}
