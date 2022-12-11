package tech.reliab.course.vitokhinkaLab.bank.service.impl;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.CreditAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.DeletingNotExistentObjectExcepetion;
import tech.reliab.course.vitokhinkaLab.bank.service.UserService;

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
        double salary = random.nextInt(10000);
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

    public void addCreditAccout(User user, CreditAccount credit){
        user.getCreditAccounts().add(credit);
    }

    public void delCreditAccout(User user, CreditAccount credit){
        user.getCreditAccounts().remove(credit);
    }

    public void addPaymentAccount(User user, PaymentAccount paymentAccount){
        user.getPaymentAccounts().add(paymentAccount);
    }

    public void delPaymentAccount(User user, PaymentAccount paymentAccount){
        user.getPaymentAccounts().remove(paymentAccount);
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

}
