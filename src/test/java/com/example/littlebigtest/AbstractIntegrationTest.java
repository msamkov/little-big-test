package com.example.littlebigtest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
public abstract class AbstractIntegrationTest {

    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.2")
            .withDatabaseName("integration-tests-db")
            .withUsername("db_user")
            .withPassword("db_pass");

    static {
        postgreSQLContainer.start();
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.jpa.show-sql=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}


