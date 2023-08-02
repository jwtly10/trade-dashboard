package com.jwtly10.tradedashboard.dao;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.jwtly10.tradedashboard.model.Account;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountDaoImplTest {

    private DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript("schema-h2.sql")
            .continueOnError(true)
            .build();

    private AccountDAOImpl accountDAOImpl = new AccountDAOImpl(new JdbcTemplate(dataSource));

    @Test
    public void AccountDAOImpl_SaveAccount() {
        Account account = Account.builder()
                .accountID(4098434)
                .accountKey("48093480938409384039843098")
                .accountSize(10000)
                .accountType("LIVE")
                .build();

        Account savedAccount = accountDAOImpl.create(account);

        Assertions.assertThat(savedAccount).isNotNull();
        Assertions.assertThat(savedAccount.getAccountID()).isGreaterThan(0);
    }

    @Test
    public void AccountDAOImpl_ReadAllAccounts() {
        Account account = Account.builder()
                .accountID(40090493)
                .accountKey("48093480938409384039843098")
                .accountSize(10000)
                .accountType("LIVE")
                .build();

        accountDAOImpl.create(account);
        Assertions.assertThat(accountDAOImpl.list()).isNotNull();
        Assertions.assertThat(accountDAOImpl.list().size()).isEqualTo(2);
    }
}
