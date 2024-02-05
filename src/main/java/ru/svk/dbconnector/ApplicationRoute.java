package ru.svk.dbconnector;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public final class ApplicationRoute extends RouteBuilder {
    private final String dbUser;
    private final String dbPassword;

    public ApplicationRoute(CamelContext context, String dbUser, String dbPassword) {
        super(context);
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    @Override
    public void configure() throws Exception {
        from("timer:start?delay=0&period=10000")
//                .setBody(constant("select * from users;"))
                .to("jdbc2:h2svk?datasourceUrl=jdbc:h2:mem:svkdb&user=" + dbUser + '&' + "password=" + dbPassword)
                .split(body())
                .log(LoggingLevel.INFO, "Got from database: ${body}");
    }
}
