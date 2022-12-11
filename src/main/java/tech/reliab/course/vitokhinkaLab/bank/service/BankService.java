package tech.reliab.course.vitokhinkaLab.bank.service;
import tech.reliab.course.vitokhinkaLab.bank.entity.*;

import java.io.IOException;
import java.util.List;

public interface BankService {

    Bank create(String name);

    void outputBankInfo(Bank bank);

    void addOffice(Bank bank, BankOffice office);

    void deleteOffice(Bank bank, BankOffice office);

    void addAtm(Bank bank, BankAtm atm);

    void deleteAtm(Bank bank, BankAtm atm);

    void addEmployee(Bank bank, Employee employee);

    void deleteEmployee(Bank bank, Employee employee);

    void addUser(Bank bank, User user);

    void deleteUser(Bank bank, User user);

    List<BankOffice> getOfficesForLoans(Bank bank, double sum);

    List<Employee> getEmployeesForLoans(Bank bank, BankOffice office);

    void getCredit(List<Bank> banks, User user);

    void exportBankAccounts(Bank bank, String fileName) throws IOException;

    void importBankAccounts(Bank bank, String fileName) throws IOException;



}