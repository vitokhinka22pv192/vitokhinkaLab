package tech.reliab.course.vitokhinkaLab.bank.service;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;


public interface PaymentAccountService {

    PaymentAccount create(User user, Bank bank);
    PaymentAccount read();
    void update(PaymentAccount paymentAccount);
    void delete(PaymentAccount paymentAccount);
}
