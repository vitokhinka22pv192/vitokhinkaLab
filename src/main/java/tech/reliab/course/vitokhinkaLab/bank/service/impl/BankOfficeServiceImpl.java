package tech.reliab.course.vitokhinkaLab.bank.service.impl;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.BankOffice;
import tech.reliab.course.vitokhinkaLab.bank.service.BankOfficeService;
import tech.reliab.course.vitokhinkaLab.bank.entity.BankAtm;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.NegativeSumException;
import tech.reliab.course.vitokhinkaLab.bank.exceptions.DeletingNotExistentObjectExcepetion;

import java.util.List;

/**
 *  Singleton
 */
public class BankOfficeServiceImpl implements BankOfficeService {

    private static  BankOfficeServiceImpl INSTANCE;

    private BankOfficeServiceImpl(){}

    public static BankOfficeServiceImpl getInstance(){
        if (INSTANCE==null){
            INSTANCE = new BankOfficeServiceImpl();
        }
        return INSTANCE;
    }

    Long id = 0L;
    private BankOffice bankOffice;

    @Override
    public BankOffice create(String name, Bank bank, String address, double rent){
        var bankOffice = new BankOffice(
                ++id,
                bank,
                name,
                address,
                true,
                true,
                true,
                true,
                true,
                bank.getMoneyAmount(),
                rent
        );
        bank.getListOfOffices().add(bankOffice);
        return bankOffice;
    }

    /**
     *
     * @return - возвращает объект офис
     */
    @Override
    public BankOffice read(){
        return bankOffice;
    }

    /**
     *
     * @param bankOffice - новый объект
     */
    @Override
    public  void update(BankOffice bankOffice){
        this.bankOffice = bankOffice;
    }

    /**
     *
     * @param bankOffice - офис для удалеия
     */
    @Override
    public void delete(BankOffice bankOffice){
        if(bankOffice == this.bankOffice){
            this.bankOffice = null;
        }
    }
    @Override
    public void addAtm(BankOffice office, BankAtm atm) {
        office.getAtmList().add(atm);
    }

    @Override
    public void deleteAtm(BankOffice office, BankAtm atm) {
        if(!office.getAtmList().contains(atm)){
            throw new DeletingNotExistentObjectExcepetion();
        }
        office.getAtmList().remove(atm);
    }

    @Override
    public List<BankAtm> getAtmsForLoans(BankOffice office, double sum) {
        if(sum < 0){
            throw new NegativeSumException();
        }
        return office.getAtmList().stream().filter(
                atm-> atm.isCanPaymentOfMoney() && atm.getMoneyAmount() > sum).toList();
    }

}