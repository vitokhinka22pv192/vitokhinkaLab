package tech.reliab.course.vitokhinkaLab;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;
import tech.reliab.course.vitokhinkaLab.bank.service.*;
import tech.reliab.course.vitokhinkaLab.bank.service.impl.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Сервисы
        BankService bankService = BankServiceImpl.getInstance();
        BankOfficeServiceImpl officeService = BankOfficeServiceImpl.getInstance();
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        BankAtmService atmService = BankAtmServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();
        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();

        List<Bank> bankList = new ArrayList<>();

        //Создание сущьностей
        for(int numBunk=0; numBunk<5; numBunk++){
            var bank = bankService.create("Bank"+(numBunk+1));
            for(int numOffice=0; numOffice<3; numOffice++){
                var office = officeService.create(
                        "Office"+(numOffice+1),
                        bank,
                        "Address",
                        1000.
                );
                for(int numEmpl=0; numEmpl<5; numEmpl++){
                    var employee = employeeService.create(
                            "kirill",
                            "vitokhin",
                            LocalDate.now(),
                            "engeneer",
                            bank,
                            office,
                            10000.
                    );
                }
            }
            for(int numAtm=0; numAtm<3; numAtm++){
                var atm = atmService.create(
                        "atm"+(numAtm+1),
                        bank,
                        bank.getListOfOffices().stream().findFirst().get(),
                        bank.getListOfEmployees().stream().findFirst().get(),
                        100.
                );
            }
            for(int numUser=0; numUser<5; numUser++){
                var user = userService.create(
                        "user",
                        "user",
                        LocalDate.now(),
                        "job"
                );
                bankService.addUser(bank, user);
                for(int numPay=0; numPay<2; numPay++){
                    var paymentAccount = paymentAccountService.create(
                            user,
                            bank
                    );
                    var credit = creditAccountService.create(
                            user,
                            bank,
                            LocalDate.now(),
                            LocalDate.now(),
                            12,
                            100000.,
                            1000.,
                            bank.getListOfEmployees().stream().findFirst().get(),
                            paymentAccount
                    );
                }
            }
            bankList.add(bank);
        }


        try{
            bankService.exportBankAccounts(bankList.get(2), "./test.txt");
            bankService.importBankAccounts(bankList.get(3), "./test.txt");
        }catch (IOException e){
            System.out.println(e);
        }
        bankService.outputBankInfo(bankList.get(1));

    }
}