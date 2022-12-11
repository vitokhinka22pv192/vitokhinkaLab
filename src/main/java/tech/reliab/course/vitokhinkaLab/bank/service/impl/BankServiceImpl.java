package tech.reliab.course.vitokhinkaLab.bank.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import tech.reliab.course.vitokhinkaLab.bank.entity.*;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.DeletingNotExistentObjectExcepetion;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.IdException;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.LendingTermsException;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.NegativeSumException;
import tech.reliab.course.vitokhinkaLab.bank.service.BankService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *  Singleton
 */
public class BankServiceImpl implements BankService {

    private static  BankServiceImpl INSTANCE;

    private BankServiceImpl(){}

    public static BankServiceImpl getInstance(){
        if (INSTANCE==null){
            INSTANCE = new BankServiceImpl();
        }
        return INSTANCE;
    }

    private Long id = 0L;
    private static Random random = new Random();

    @Override
    public Bank create(String name){
        int rating = random.nextInt(100);
        int money = random.nextInt(200000);
        double rate = (20 - (20 * rating)/100.0);
        var bank = new Bank(
                ++id,
                name,
                rating,
                money,
                rate
        );

        return bank;
    }

    @Override
    public void outputBankInfo(Bank bank){
        System.out.println("Bank:");
        System.out.println("\t"+bank);
        System.out.println("\tOffices:");
        for(var office: bank.getListOfOffices()){
            System.out.println("\t\t"+office);
        }
        System.out.println("\tEmployees:");
        for(var employee: bank.getListOfEmployees()){
            System.out.println("\t\t"+employee);
        }
        System.out.println("\tAtms:");
        for(var atm: bank.getListOfAtms()){
            System.out.println("\t\t"+atm);
        }
        System.out.println("\tUsers:");
        for(var user: bank.getListOfClients()){
            UserServiceImpl.getInstance().outputUserInfo(user);
        }
    }

    @Override
    public void addOffice(Bank bank, BankOffice office) {
        bank.getListOfOffices().add(office);
    }

    @Override
    public void deleteOffice(Bank bank, BankOffice office){
        if(!bank.getListOfOffices().contains(office)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        bank.getListOfOffices().remove(office);
    }

    @Override
    public void addAtm(Bank bank, BankAtm atm) {
        bank.getListOfAtms().add(atm);
    }

    @Override
    public void deleteAtm(Bank bank, BankAtm atm) {
        if(!bank.getListOfAtms().contains(atm)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        bank.getListOfAtms().remove(atm);
    }

    @Override
    public void addEmployee(Bank bank, Employee employee) {
        bank.getListOfEmployees().add(employee);
    }

    @Override
    public void deleteEmployee(Bank bank, Employee employee) {
        if(!bank.getListOfEmployees().contains(employee)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        bank.getListOfEmployees().remove(employee);
    }

    @Override
    public void addUser(Bank bank, User user) {
        boolean userExists = !bank.getListOfClients().stream().filter(
                _user -> _user.getId().compareTo(user.getId())==0).toList().isEmpty();
        if(!userExists){
            bank.getListOfClients().add(user);
        }
    }

    @Override
    public void deleteUser(Bank bank, User user) {
        if(!bank.getListOfClients().contains(user)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        bank.getListOfClients().remove(user);
    }

    @Override
    public List<BankOffice> getOfficesForLoans(Bank bank, double sum) {
        if(sum < 0){
            throw new NegativeSumException();
        }
        return bank.getListOfOffices().stream().filter(
                office -> office.isCanApplyLoan() && office.getMoneyAmount() > sum && office.isWorking()).toList();
    }

    @Override
    public List<Employee> getEmployeesForLoans(Bank bank, BankOffice office) {
        if(!bank.getListOfOffices().contains(office)){
            throw new IdException();
        }
        return bank.getListOfEmployees().stream().filter(
                emp -> emp.getBankOffice().getId().compareTo(office.getId())==0 && emp.isCanApplyLoan()).toList();
    }

    @Override
    public void getCredit(List<Bank> banks, User user) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Input credit sum: ");
        double sum = reader.nextDouble();
        System.out.println("Banks:");
        for(int i=0; i<banks.size(); i++){
            System.out.println(String.format("index: %d  ",i)+banks.get(i));
        }
        System.out.println("Choose bank: ");
        int indexOfBank = reader.nextInt();
        Bank bank = banks.get(indexOfBank);
        System.out.println("Offices:");
        List<BankOffice> offices = BankServiceImpl.getInstance().getOfficesForLoans(bank, sum);
        if(offices.isEmpty()){
            System.out.println("Offices not found");
            throw new LendingTermsException();
        }
        for(int i=0; i<bank.getListOfOffices().size(); i++){
            System.out.println(String.format("index: %d  ",i)+bank.getListOfOffices().get(i));
        }
        System.out.println("Choose office: ");
        int indexOfOffice = reader.nextInt();
        BankOffice office = offices.get(indexOfOffice);
        List<Employee> employees = BankServiceImpl.getInstance().getEmployeesForLoans(bank, office);
        if(employees.isEmpty()){
            System.out.println("Employees not found");
            throw new LendingTermsException();
        }
        for(int i=0; i<employees.size(); i++){
            System.out.println(String.format("index: %d  ",i)+employees.get(i));
        }
        System.out.println("Choose employee: ");
        int indexOfEmployee = reader.nextInt();
        Employee employee = employees.get(indexOfEmployee);
        List<BankAtm> atms = BankOfficeServiceImpl.getInstance().getAtmsForLoans(office, sum);
        if(atms.isEmpty()){
            System.out.println("Atms not found");
            throw new LendingTermsException();
        }
        if(!user.getBanks().contains(bank)){
            BankServiceImpl.getInstance().addUser(bank, user);
            PaymentAccount payment = PaymentAccountServiceImpl.getInstance().create(user,bank);
        }
        if(user.getCreditRating() < 5000 && bank.getRating() > 50){
            throw new LendingTermsException();
        }
        int month = Math.toIntExact(Math.round(sum/ user.getSalary()));
        CreditAccount creditAccount = CreditAccountServiceImpl.getInstance().create(
                user,
                bank,
                LocalDate.now(),
                LocalDate.now(),
                month,
                sum,
                sum/12,
                employee,
                user.getPaymentAccounts().stream().filter(
                        pay -> pay.getBank().getId().compareTo(bank.getId())==0).findFirst().get()
        );
        BankAtmServiceImpl.getInstance().withdrawMoney(atms.get(0), sum);
    }

    @Override
    public void exportBankAccounts(Bank bank, String fileName) throws IOException {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        var paymentAccounts = new ArrayList<PaymentAccount>();
        var creditAccounts = new ArrayList<CreditAccount>();
        for(var user: bank.getListOfClients()){
            paymentAccounts.addAll(user.getPaymentAccounts());
            creditAccounts.addAll(user.getCreditAccounts());
        }
        Accounts accounts = new Accounts(paymentAccounts, creditAccounts);
        objectMapper.writeValue(file, accounts);
    }

    @Override
    public void importBankAccounts(Bank bank, String fileName) throws IOException {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Accounts accounts = objectMapper.readValue(file, Accounts.class);
        var payments = accounts.paymentAccounts;
        var credits = accounts.creditAccounts;
        var users = new ArrayList<User>();

        for(var payment : payments){
            payment.getUser().addBank(bank);
            payment.setBank(bank);
            if(users.stream().filter(user->user.getId().compareTo(payment.getUser().getId())==0).toList().isEmpty()){
                users.add(payment.getUser());
                users.get(users.size()-1).addPaymentAccount(payment);
            }else{
                users.stream().filter(user -> user.getId().compareTo(
                        payment.getUser().getId())==0).findFirst().orElseThrow().addPaymentAccount(payment);
            }
        }
        for(var credit: credits){
            credit.getUser().addBank(bank);
            credit.setBank(bank);
            if(users.stream().filter(user->user.getId().compareTo(credit.getUser().getId())==0).toList().isEmpty()){
                users.add(credit.getUser());
                users.get(users.size()-1).addCreditAccount(credit);
            }else{
                users.stream().filter(user -> user.getId().compareTo(
                        credit.getUser().getId())==0).findFirst().orElseThrow().addCreditAccount(credit);
            }
        }
        for(var user: users){
            addUser(bank, user);
        }
    }

    private static class Accounts{
        public List<PaymentAccount> paymentAccounts;
        public List<CreditAccount> creditAccounts;

        public Accounts(){};

        public Accounts(List<PaymentAccount> paymentAccounts, List<CreditAccount> creditAccounts){
            this.creditAccounts = creditAccounts;
            this.paymentAccounts = paymentAccounts;
        }


    }
}