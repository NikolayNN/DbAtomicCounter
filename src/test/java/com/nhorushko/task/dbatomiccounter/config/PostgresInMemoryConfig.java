package com.nhorushko.task.dbatomiccounter.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.IOException;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;

@TestConfiguration
public class PostgresInMemoryConfig {
    @Bean
    public DataSource dataSource() throws IOException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().start();
        return pg.getPostgresDatabase();
    }
}
