package com.example.test_task.repository;

import com.example.test_task.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "select acc \n" +
            "from ACCOUNT as acc \n" +
            "where acc.accountId = :accountId")
    Account getAccountByAccountId(@Param("accountId") Integer Id);

}
