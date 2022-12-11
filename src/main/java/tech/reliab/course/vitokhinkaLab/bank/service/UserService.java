package tech.reliab.course.vitokhinkaLab.bank.service;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;

import java.io.IOException;
import java.time.LocalDate;

public interface UserService {

    User create(String firstName, String lastName, LocalDate birthDate, String job);

    User create(String firstName, String lastName, String patronymic, LocalDate birthDate, String job);

    void outputUserInfo(User user);

    void addBank(User user, Bank bank);

    void deleteBank(User user, Bank bank);

    void outputUserAccountsToFile(User user, Bank bank, String fileName) throws IOException;

}