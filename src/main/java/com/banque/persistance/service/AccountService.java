package com.banque.persistance.service;

import com.banque.persistance.model.Account;
import com.banque.persistance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(Integer id){
        return accountRepository.findById(id);
    }

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public void deleteAccount(Integer id){
        accountRepository.deleteById(id);
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }

    public Account addCredit(Integer id, BigDecimal amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal currentCredit = account.getCredit() != null ? account.getCredit() : BigDecimal.ZERO;
            account.setCredit(currentCredit.add(amount));
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }
    }
}
