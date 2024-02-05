package ru.svk.dbconnector.persistence.repository;

import ru.svk.dbconnector.persistence.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This repo is just example to
 */
public class UserRepository extends Repository<User> {
    private static final String TABLE_NAME = "USERS";

    public UserRepository(DataSource dataSource) {
        super(dataSource);
    }

    public List<User> findAll() throws SQLException {
        return doQuery("select * from " + TABLE_NAME + ";");
    }

    @Override
    protected List<User> extractResultSet(ResultSet rs) {
        try {
            List<User> data = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                data.add(user);
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException("ResultSet to Map parsing error", e);
        }
    }
}
