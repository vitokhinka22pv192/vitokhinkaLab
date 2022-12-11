package tech.reliab.course.vitokhinkaLab.bank.service.impl;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.DeletingNotExistentObjectExcepetion;
import tech.reliab.course.vitokhinkaLab.bank.service.UserService;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Random;

/**
 *  Singleton
 */
public class UserServiceImpl implements UserService {

    private static  UserServiceImpl INSTANCE;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance(){
        if (INSTANCE==null){
            INSTANCE = new UserServiceImpl();
        }
        return INSTANCE;
    }
    private Long id = 0L;
    private Random random = new Random();

    @Override
    public User create(String firstName, String lastName, LocalDate birthDate, String job){
        double salary = random.nextInt(100000);
        var user = new User(
                ++id,
                firstName,
                lastName,
                birthDate,
                job,
                salary,
                salary / 10
        );
        return user;
    }

    @Override
    public User create(String firstName, String lastName, String patronymic, LocalDate birthDate, String job){
        double salary = random.nextInt(10000);
        var user = new User(
                ++id,
                firstName,
                lastName,
                patronymic,
                birthDate,
                job,
                salary,
                salary / 10
        );
        return user;
    }

    @Override
    public void outputUserInfo(User user) {
        System.out.println("User:");
        System.out.println("\t"+user);
        System.out.println("\tPayment Accounts:");
        for(var payment: user.getPaymentAccounts()){
            System.out.println("\t\t"+payment);
        }
        System.out.println("\tCredit Accounts:");
        for(var credit: user.getCreditAccounts()){
            System.out.println("\t\t"+credit);
        }
    }

    @Override
    public void addBank(User user, Bank bank) {
        user.getBanks().add(bank);
    }

    @Override
    public void deleteBank(User user, Bank bank) {
        if(!user.getBanks().contains(bank)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        user.getBanks().remove(bank);
    }

    @Override
    public void outputUserAccountsToFile(User user, Bank bank, String fileName) throws IOException {
        File file = new File(fileName);
        PrintStream printStream = new PrintStream(file);
        printStream.printf("User: %s\n", user.getFullName());
        var payments = user.getPaymentAccounts().stream().filter(
                pay -> pay.getBank().getId().compareTo(bank.getId())==0).toList();
        if(payments.isEmpty()){
            printStream.println("\tUser does not have payment accounts");
        }else{
            printStream.println("\tPayment accounts:");
            payments.forEach(printStream::println);
        }

        var credits = user.getCreditAccounts().stream().filter(
                credit -> credit.getBank().getId().compareTo(bank.getId())==0).toList();
        if(payments.isEmpty()){
            printStream.println("\tUser does not have credit accounts");
        }else{
            printStream.println("\tCredit accounts:");
            credits.forEach(printStream::println);
        }
    }


}