package com.ftgo.AccountingService.domain.account.repository;

import com.ftgo.AccountingService.domain.account.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query(value = "SELECT * FROM accounts WHERE consumer_id = consumerId", nativeQuery = true)
    public List<Account> findByConsumerId(long consumerId);
}
