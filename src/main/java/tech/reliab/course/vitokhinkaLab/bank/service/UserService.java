package tech.reliab.course.vitokhinkaLab.bank.service;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.CreditAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;

import java.time.LocalDate;

public interface UserService {
    User create(String firstName, String lastName, LocalDate birthDate, String job);

    User create(String firstName, String lastName, String patronymic, LocalDate birthDate, String job);
    void addCreditAccout(User user, CreditAccount credit);

    void delCreditAccout(User user, CreditAccount credit);

    void addPaymentAccount(User user, PaymentAccount paymentAccount);

    void delPaymentAccount(User user, PaymentAccount paymentAccount);

    void outputUserInfo(User user);

    void addBank(User user, Bank bank);
    void deleteBank(User user, Bank bank);
}
