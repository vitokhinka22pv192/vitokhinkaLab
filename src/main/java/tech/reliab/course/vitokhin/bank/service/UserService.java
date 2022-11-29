package tech.reliab.course.vitokhin.bank.service;

import tech.reliab.course.vitokhin.bank.entity.Bank;
import tech.reliab.course.vitokhin.bank.entity.CreditAccount;
import tech.reliab.course.vitokhin.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhin.bank.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    /**
     *
     * @param firstName - имя
     * @param lastName - фамилия
     * @param birthDate - дата рождения клиента
     * @param job - место работы
     * @param bank - банк
     * @return - возвращает созданный объект клиент
     */
    User create(String firstName, String lastName, LocalDate birthDate, String job, Bank bank);
    /**
     *
     * @param firstName - имя
     * @param lastName - фамилия
     * @param patronymic - отчество
     * @param birthDate - дата рождения клиента
     * @param job - место работы
     * @param bank - банк
     * @return - возвращает созданный объект клиент
     */
    User create(String firstName, String lastName, String patronymic, LocalDate birthDate, String job, Bank bank);

    void addCreditAccout(User user, CreditAccount credit);

    void delCreditAccout(User user, CreditAccount credit);

    void addPaymentAccount(User user, PaymentAccount paymentAccount);

    void delPaymentAccount(User user, PaymentAccount paymentAccount);

    void outputUserInfo(User user);

}
