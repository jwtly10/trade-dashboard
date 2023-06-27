package com.jwtly10.tradedashboard.dao;

import com.jwtly10.tradedashboard.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAOImpl implements AccountDAO<Account>{
    private static final Logger log = LoggerFactory.getLogger(AccountDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Account> rowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setAccountID(rs.getInt("accountID"));
        account.setAccountKey(rs.getString("accountKey"));
        account.setAccountSize(rs.getInt("accountSize"));
        account.setAccountType(rs.getString("accountType"));
        account.setCreated(rs.getTimestamp("created"));
        return account;
    };

    public AccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> list() {
        String sql = """
                SELECT accountID, accountKey, accountSize, accountType, created
                FROM accounts_tb;
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Account account) {
        String sql = """
                INSERT INTO accounts_tb
                (accountID, accountKey, accountSize, accountType)
                VALUES
                (?,?,?,?);
                """;
       int res = jdbcTemplate.update(sql,
               account.getAccountID(),
               account.getAccountKey(),
               account.getAccountSize());
       if (res == 1){
           log.info("New account created: " + account.getAccountID());
       }else {
           log.info("New account failed to create: " + account.getAccountID());
       }
    }

    @Override
    public Optional<Account> get(int accountID) {
        String sql = """
                SELECT accountID, accountKey, accountSize, accountType, created
                FROM accounts_tb WHERE accountID = ?;
                """;
        Account account = null;
        try{
            account = jdbcTemplate.queryForObject(sql, rowMapper, accountID);
        }catch (DataAccessException ex){
            log.info("Account not for found for accountID: " + accountID);
        }
        return Optional.ofNullable(account);
    }

    @Override
    public int update(Account account, int id) {
        return 0;
    }

    @Override
    public int delete(int accountID) {
        return jdbcTemplate.update("""
        DELETE FROM accounts_tb WHERE accountID = ?;
        """, accountID);
    }
}
