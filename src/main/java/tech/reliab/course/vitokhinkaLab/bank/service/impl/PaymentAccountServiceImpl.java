package tech.reliab.course.vitokhinkaLab.bank.service.impl;

import tech.reliab.course.vitokhinkaLab.bank.entity.Bank;
import tech.reliab.course.vitokhinkaLab.bank.entity.PaymentAccount;
import tech.reliab.course.vitokhinkaLab.bank.entity.User;
import tech.reliab.course.vitokhinkaLab.bank.service.PaymentAccountService;

/**
 *  Singleton
 */
public class PaymentAccountServiceImpl implements PaymentAccountService {
    private static  PaymentAccountServiceImpl INSTANCE;

    private PaymentAccountServiceImpl(){}

    public static PaymentAccountServiceImpl getInstance(){
        if (INSTANCE==null){
            INSTANCE = new PaymentAccountServiceImpl();
        }
        return INSTANCE;
    }

    private Long id = 0L;
    private PaymentAccount paymentAccount;

    @Override
    public PaymentAccount create(User user, Bank bank){
        var paymentAccount = new PaymentAccount(
                ++id,
                user,
                bank,
                0
        );
        if(!user.getBanks().contains(bank)){
            user.addBank(bank);
        }
        user.getPaymentAccounts().add(paymentAccount);
        return paymentAccount;
    }
    /**
     *
     * @return - возвращает платежный счет
     */
    @Override
    public PaymentAccount read(){
        return paymentAccount;
    }

    /**
     *
     * @param paymentAccount - новый платежный счет
     */
    @Override
    public void update(PaymentAccount paymentAccount){
        this.paymentAccount = paymentAccount;
    }

    /**
     *
     * @param paymentAccount - платежный счет для удаления
     */
    @Override
    public void delete(PaymentAccount paymentAccount){
        if(this.paymentAccount == paymentAccount){
            this.paymentAccount = null;
        }
    }
}
