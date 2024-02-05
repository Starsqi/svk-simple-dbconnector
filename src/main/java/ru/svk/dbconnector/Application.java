package ru.svk.dbconnector;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.svk.dbconnector.persistence.H2DataSource;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start application...");
        String dbUser = System.getenv("dbUser");
        String dbPassword = System.getenv("dbPassword");
        H2DataSource h2DataSource = new H2DataSource("jdbc:h2:mem:svkdb", dbUser, dbPassword);
        h2DataSource.initDb();
        logger.info("Database initialization finished!");

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("jdbc2", new Jdbc2Component(camelContext));
        camelContext.addRoutes(new ApplicationRoute(camelContext, dbUser, dbPassword));
        camelContext.start();

        logger.info("Camel context started...");
        Thread.sleep(60_000);//to wait for result of route
        logger.info("Closing Camel context...");
        camelContext.close();
        logger.info("Camel context closed...");
    }
}
