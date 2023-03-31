package com.nhorushko.task.dbatomiccounter.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class CounterRepository {
    private static final String QUERY_INCREMENT_COUNTER = """
            UPDATE sk_example_table
            SET obj = jsonb_set(obj, '{current}', to_jsonb(:increment + (obj->>'current')::bigint))
            WHERE id = :id
            RETURNING obj->>'current' AS current_value
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public long increment(long id, long increment) {
        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("increment", increment);
        Long current = jdbcTemplate.queryForObject(QUERY_INCREMENT_COUNTER, params, Long.class);
        return requireNonNull(current);
    }
}
