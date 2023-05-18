package com.example.demo.service;

import com.example.demo.common.Constant;
import com.example.demo.controller.AccountController;
import com.example.demo.entity.Account;
import com.example.demo.entity.dto.AccountDTO;
import com.example.demo.form.AccountFilterForm;
import com.example.demo.repository.IAccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {


    private ModelMapper modelMapper;
    private final IAccountRepository repository;
    private final IAccountService service;
    private final QueryService queryService;
//    @Override
//    public List<Account> getAllAccounts() {
//        return repository.findAll();
//    }
    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Specification<Account> where = buildWhere(form);
        Page<Account> accountPage = repository.findAll(where, pageable);

        List<AccountDTO> accountDTOList = modelMapper.map(accountPage.getContent(), new TypeToken<List<AccountDTO>>() {
        }.getType());

        for (AccountDTO account : accountDTOList) {
            account.add(linkTo(methodOn(AccountController.class).getAccountById(account.getId())).withSelfRel());
        }
        Page<AccountDTO> accountDTOPage = new PageImpl<>(accountDTOList, pageable, accountPage.getTotalElements());
        return accountDTOPage;
    }

    private Specification<Account> buildWhere(AccountFilterForm form) {
        Specification<Account> where = Specification.where(null);
        if (form.getId() != null) {
            where = where.and(queryService.buildIntegerFilter(Constant.ACCOUNT.ID, form.getId()));
        }
        if (form.getUsername() != null) {
            where = where.and(queryService.buildStringFilter(Constant.ACCOUNT.USERNAME, form.getUsername()));
        }
        if (form.getSearch() != null) {
            where = where
                    .or(queryService.buildStringFilter(Constant.ACCOUNT.USERNAME, form.getSearch()))
                    .or(queryService.buildStringFilter(Constant.ACCOUNT.FIRST_NAME, form.getSearch()))
                    .or(queryService.buildStringFilter(Constant.ACCOUNT.LAST_NAME, form.getSearch()));
        }
        return where;
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        return repository.findById(id);
    }


    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account updateAccount(Integer id, Account updatedAccount) {
        Optional<Account> optionalAccount = repository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setUsername(updatedAccount.getUsername());
            account.setPassword(updatedAccount.getPassword());
            account.setFirstName(updatedAccount.getFirstName());
            account.setLastName(updatedAccount.getLastName());
            account.setFullName(updatedAccount.getFullName());
            account.setRole(updatedAccount.getRole());
            return repository.save(account);
        }
        return null;
    }

    @Override
    public boolean doesAccountExist(Integer id) {
        return repository.existsById(id);
    }



    @Override
    public Account deleteAccountById(Integer id) {
        repository.deleteById(id);
        return null;
    }


}
