package ru.svk.dbconnector;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import ru.svk.dbconnector.persistence.repository.UserRepository;

import javax.sql.DataSource;

public class Jdbc2Producer extends DefaultProducer {

    private UserRepository userRepository;

    public Jdbc2Producer(Endpoint endpoint, DataSource dataSource) {
        super(endpoint);
        this.userRepository = new UserRepository(dataSource);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getMessage().setBody(userRepository.findAll());
    }
}
