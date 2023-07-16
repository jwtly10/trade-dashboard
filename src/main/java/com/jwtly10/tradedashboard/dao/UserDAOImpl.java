package com.jwtly10.tradedashboard.dao;

import com.jwtly10.tradedashboard.model.Account;
import com.jwtly10.tradedashboard.model.Role;
import com.jwtly10.tradedashboard.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId((rs.getInt("id")));
        user.setFirst_name((rs.getString("first_name")));
        user.setLast_name((rs.getString("last_name")));
        user.setEmail((rs.getString("email")));
        user.setPassword((rs.getString("password")));
        user.setRole(Role.valueOf((rs.getString("role"))));
        return user;
    };

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public void create(User user) {
        String sql = """
                INSERT INTO users_tb
                (first_name, last_name, email, password, role)
                VALUES
                (?,?,?,?,?);
                """;
        int res = jdbcTemplate.update(sql,
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().toString());
        if (res == 1) {
            log.info("New user created: " + user.getEmail());
        } else {
            log.info("New user failed to create: " + user.getEmail());
        }
    }

    @Override
    public Optional<User> get(String username) {
        String sql = """
                SELECT id, first_name, last_name, email, password, role
                FROM users_tb WHERE email = ?;
                """;
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, username);
        } catch (DataAccessException ex) {
            log.info("User not for found for email: " + username);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public int update(User user, int id) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
