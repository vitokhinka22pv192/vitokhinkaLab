package tech.reliab.course.vitokhinkaLab.bank.service;

import tech.reliab.course.vitokhinkaLab.bank.entity.*;

import java.time.LocalDate;

public interface CreditAccountService {
    /**
     *
     * @param user - клиент
     * @param bank - банк
     * @param start - дата начала кредита
     * @param end - дата окончания кредита
     * @param month - кол-во месяцев
     * @param sum - сумма кредита
     * @param monthPayment - ежемесячный платеж
     * @param employee - сотрудник выдавший кредит
     * @param paymentAccount - платежный счет
     * @return - возвращает созданный кредитный счет
     */
    CreditAccount create(User user,
                         Bank bank,
                         LocalDate start,
                         LocalDate end,
                         int month,
                         double sum,
                         double monthPayment,
                         Employee employee,
                         PaymentAccount paymentAccount
    );
    CreditAccount read();
    void update(CreditAccount creditAccount);
    void delete(CreditAccount creditAccount);
}
