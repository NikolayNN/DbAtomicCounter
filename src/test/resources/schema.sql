DROP TABLE IF EXISTS sk_example_table;

CREATE TABLE sk_example_table(
    id  SERIAL PRIMARY KEY,
    obj jsonb NOT NULL
);

