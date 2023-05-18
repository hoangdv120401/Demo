package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.dto.AccountDTO;
import com.example.demo.form.AccountFilterForm;
import com.example.demo.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private final IAccountService service;

    @GetMapping()
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Page<AccountDTO> accountDTOPage = service.getAllAccounts(pageable,form);
        return ResponseEntity
                .ok()
                .body(accountDTOPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable(name = "id") Integer id) {
        Optional<Account> account = service.getAccountById(id);

        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account accountCreating = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "id") Integer id, @RequestBody Account account) {
        boolean accountExists = service.doesAccountExist(id);

        if (accountExists) {
            Account account1 = service.updateAccount(id, account);
            return ResponseEntity.ok().body(account1);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable(name = "id") Integer id) {
        boolean accountExists = service.doesAccountExist(id);
        if (accountExists) {
            Account account = service.deleteAccountById(id);
            return ResponseEntity.ok().body(account);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
