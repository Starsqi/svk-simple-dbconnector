package ru.svk.dbconnector;

import org.apache.camel.*;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Endpoint example:
 * to(jdbc2:foo?datasourceUrl=jdbc:h2:mem:svkdb&username=sa&password=)
 */
@UriEndpoint(firstVersion = "1.0.0",
        scheme = "jdbc2",
        syntax = "jdbc2:name",
        title = "Jdbc2Endpoint to get data from db",
        category = {Category.DATABASE}
)
public class Jdbc2Endpoint extends DefaultEndpoint {
    private DataSource dataSource;

    @UriPath @Metadata(required = true)
    private String name;

    @UriParam
    @Metadata(required = true, label = "dbUrl")
    private String datasourceUrl;
    @UriParam
    @Metadata(required = true, label = "creds")
    private String user;
    @UriParam
    @Metadata(required = true, label = "creds")
    private String password;

    public Jdbc2Endpoint(String endpointUri, String remaining, DataSource dataSource, Component component) {
        super(endpointUri, component);
        this.name = remaining;
        this.dataSource = dataSource;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new Jdbc2Producer(this, dataSource);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("Not supported consumer");
    }

    /**
     * some doc
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *  some doc
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * some doc
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * some doc
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    /**
     * some doc
     * @param datasourceUrl
     */
    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl;
    }

    public String getUser() {
        return user;
    }

    /**
     * some doc
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    /**
     * some doc
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void close() throws IOException {
        super.close();
        //close DB
    }
}
