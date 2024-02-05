package ru.svk.dbconnector.persistence.repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Repository<Entity> {
    private final DataSource dataSource;

    protected Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected List<Entity> doQuery(String query, Object... params) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                if (params != null) {
                    for (int i = 1; i <= params.length; i++) {
                        statement.setObject(i, params[i - 1]);
                    }
                }
                try (ResultSet rs = statement.executeQuery()) {
                    return extractResultSet(rs);
                }
            }
        }
    }

    protected abstract List<Entity> extractResultSet(ResultSet rs);
}
