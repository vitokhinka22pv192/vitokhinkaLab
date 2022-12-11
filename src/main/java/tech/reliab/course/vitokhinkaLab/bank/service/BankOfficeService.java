package tech.reliab.course.vitokhinkaLab.bank.service;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.BankOffice;
import tech.reliab.course.vitokhinkaLab.bank.entity.BankAtm;

import java.util.List;

public interface BankOfficeService {
    /**
     * @param name    - имя офиса
     * @param bank    - банк офиса
     * @param address - адрес офиса
     * @param rent    - стоимость аренды офиса
     * @return - возвращает созданный объект офис
     */
    BankOffice create(String name, Bank bank, String address, double rent);

    BankOffice read();

    void update(BankOffice bankOffice);

    void delete(BankOffice bankOffice);

    void addAtm(BankOffice office, BankAtm atm);

    /**
     * Удаляем банкомат в офиса
     * @param office - офис
     * @param atm - банкомат
     */
    void deleteAtm(BankOffice office, BankAtm atm);

    /**
     *
     * @param office - офис
     * @param sum - сумма кредита
     * @return - возвращает список банкоматов, которые могут выдать данную сумму
     */
    List<BankAtm> getAtmsForLoans(BankOffice office, double sum);
}