package com.jwtly10.tradedashboard.service;

import com.jwtly10.tradedashboard.dao.AccountDAOImpl;
import com.jwtly10.tradedashboard.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountDAOImpl accountDaoImpl;

    public AccountService(AccountDAOImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    public List<Account> getAccounts(){
        return accountDaoImpl.list();
    }

    public Optional<Account> getAccountKey(int accountID){
        return accountDaoImpl.get(accountID);
    }

    public void createAccount(Account account){
        accountDaoImpl.create(account);
    }

    public int deleteAccount(int accountID){
        return accountDaoImpl.delete(accountID);
    }
}
