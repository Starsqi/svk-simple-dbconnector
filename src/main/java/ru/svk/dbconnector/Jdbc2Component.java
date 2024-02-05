package ru.svk.dbconnector;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import ru.svk.dbconnector.persistence.H2DataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Psql 'jdbc2' component
 */
@Component("jdbc2")
public class Jdbc2Component extends DefaultComponent {
    private DataSource dataSource;

    public Jdbc2Component(CamelContext context) {
        super(context);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        if (dataSource == null) {
            String datasourceUrl = getAndRemoveParameter(parameters, "datasourceUrl", String.class);
            String jdbc2User = getAndRemoveParameter(parameters, "user", String.class);
            String jdbc2Password = getAndRemoveParameter(parameters, "password", String.class);
            dataSource = new H2DataSource(datasourceUrl, jdbc2User, jdbc2Password);
        }

        Jdbc2Endpoint jdbc2Endpoint = new Jdbc2Endpoint(uri, remaining, dataSource, this);
        setProperties(jdbc2Endpoint, parameters);
        return jdbc2Endpoint;
    }

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
}
