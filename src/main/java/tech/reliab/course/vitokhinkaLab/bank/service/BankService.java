package tech.reliab.course.vitokhinkaLab.bank.service;
import tech.reliab.course.vitokhinkaLab.bank.entity.*;

import java.util.List;

public interface BankService {

    Bank create(String name);

    void outputBankInfo(Bank bank);

    void addAtm(Bank bank, BankAtm bankatm);

    void addOffice(Bank bank, BankOffice bankOffice);

    void addEmployee(Bank bank, Employee employee);

    void addUser(Bank bank, User user);

    void delAtm(Bank bank, BankAtm bankatm);

    void delOffice(Bank bank, BankOffice bankOffice);

    void deleteEmployee(Bank bank, Employee employee);

    void delUser(Bank bank, User user);

    List<BankOffice> getOfficesForLoans(Bank bank, double sum);

    List<Employee> getEmployeesForLoans(Bank bank, BankOffice office);

    void getCredit(List<Bank> banks, User user);

}
