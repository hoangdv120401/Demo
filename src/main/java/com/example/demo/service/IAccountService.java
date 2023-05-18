package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.dto.AccountDTO;
import com.example.demo.form.AccountFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IAccountService {


     boolean doesAccountExist(Integer id);
//    public List<Account> getAllAccounts();
    Page<AccountDTO> getAllAccounts(Pageable pageable, AccountFilterForm form);

     Optional<Account> getAccountById(Integer id);

    Account createAccount(Account account);
     Account updateAccount(Integer id, Account account);
    Account deleteAccountById(Integer id);



}
