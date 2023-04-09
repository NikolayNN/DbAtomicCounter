# Реализация счетчика в БД

## Предварительные условия

```
CREATE TABLE sk_example_table(
    id  SERIAL PRIMARY KEY,
    obj jsonb NOT NULL
);

INSERT INTO sk_example_table (obj) 
VALUES ('{"current": 0}'::jsonb)
```

## Условие задачи

Spring приложение с обработкой POST запроса по /modify

### Запрос

```
{
    "id": <number>,
    "add": <number>
}
```

### Ответ

```
{
    "current": <number>
}
```

В процессе обработки необходимо атомарно увеличить на величину 'add' начение поля 'current' столбца 'obj' строки идентифицируемой 'id' в таблице 'sk_example_table' и вернуть полученое значение через API

В случае невозможности провести операцию, вернуть http статус 418
