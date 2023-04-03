package com.nhorushko.task.dbatomiccounter.repository;

import com.nhorushko.task.dbatomiccounter.config.PostgresInMemoryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = PostgresInMemoryConfig.class)
class CounterRepositoryTest {

    @Autowired
    private CounterRepository repository;

    @Test
    @Transactional
    @Sql("classpath:scripts/add-counter-1-with-value-10.sql")
    void name() {
        Long actual = repository.modify(1, 5);
        assertEquals(15, actual);
    }
}