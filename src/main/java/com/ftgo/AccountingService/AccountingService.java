package com.ftgo.AccountingService;

import com.ftgo.AccountingService.domain.Money;
import com.ftgo.AccountingService.domain.account.entity.Account;
import com.ftgo.AccountingService.domain.account.repository.AccountRepository;
import com.ftgo.AccountingService.exception.AccountDisabledException;
import com.ftgo.AccountingService.saga.command.AuthorizeCommandInternal;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountingService {
    private final AccountRepository accountRepository;


    public AccountingService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(long consumerId) {
        Account account = new Account(consumerId, null, Money.create(0L));
        accountRepository.save(account);
        return account;
    }

    public void updateAccount(AuthorizeCommandInternal command) {
        if (accountRepository.findByConsumerId(command.getConsumerId()).isEmpty()){
            throw new AccountDisabledException(command.getConsumerId());
        }
        else {
            Account account = new Account(command.getConsumerId(),
                    command.getOrderId(), command.getOrderTotal());
            accountRepository.save(account);
        }
    }
}
