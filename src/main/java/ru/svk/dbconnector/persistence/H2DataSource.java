package ru.svk.dbconnector.persistence;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class H2DataSource implements DataSource {
    private JdbcDataSource h2DataSource;
    private String datasourceUrl;
    private String username;
    private String password;

    public H2DataSource(String datasourceUrl, String username, String password) throws SQLException {
        this.datasourceUrl = datasourceUrl;
        this.username = username;
        this.password = password;

        h2DataSource = new JdbcDataSource();
        h2DataSource.setURL(datasourceUrl);
        h2DataSource.setUser(username);
        h2DataSource.setPassword(password);
        h2DataSource.setDescription("H2 db for test camel operations");
    }

    public void initDb() throws SQLException {
        String initialScriptPath = "classpath:init-svkdb-users.sql";
        Connection connection = DriverManager.getConnection(
                //DB_CLOSE_DELAY=-1 need cause https://stackoverflow.com/questions/5763747/h2-in-memory-database-table-not-found
                String.format(
                        "%s;INIT=RUNSCRIPT FROM '%s';DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
                        datasourceUrl,
                        initialScriptPath
                ),
                username,
                password
        );
        connection.commit();
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        return h2DataSource.getConnection();
    }

    @Override
    public Connection getConnection(String user, String password) throws SQLException {
        return h2DataSource.getConnection(user, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return h2DataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {
        h2DataSource.setLogWriter(printWriter);
    }

    @Override
    public void setLoginTimeout(int timeout) throws SQLException {
        h2DataSource.setLoginTimeout(timeout);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return h2DataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return h2DataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iFace) throws SQLException {
        return h2DataSource.unwrap(iFace);
    }

    @Override
    public boolean isWrapperFor(Class<?> iFace) throws SQLException {
        return h2DataSource.isWrapperFor(iFace);
    }
}
