package com.jwtly10.tradedashboard.dao;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jwtly10.tradedashboard.model.Account;

import java.sql.Timestamp;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountDaoImplTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountDAOImpl accountDAOImpl;


    @Test
    public void AccountDaoImpl_SaveAccount() {
        Account account = Account.builder()
                .accountID(4098434)
                .accountKey("48093480938409384039843098")
                .accountSize(10000)
                .accountType("LIVE")
                .created(Timestamp.valueOf("2018-12-12 01:02:03.123456789"))
                .build();

        Account savedAccount = accountDAOImpl.create(account);

        Assertions.assertThat(savedAccount).isNotNull();
        Assertions.assertThat(savedAccount.getAccountID()).isGreaterThan(0);


    }
}
