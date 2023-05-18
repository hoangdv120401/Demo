package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>,
                                            JpaSpecificationExecutor<Account> {


//    @Query(value = "select * from Account a order by a.username", nativeQuery = true)
//    List<Account> findAllAccountSortedByUsername(String name);

    Account findAccountByUsername(String username);


}
