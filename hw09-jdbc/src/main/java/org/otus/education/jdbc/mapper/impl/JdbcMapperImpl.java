package org.otus.education.jdbc.mapper.impl;

import lombok.SneakyThrows;
import org.otus.education.jdbc.DbExecutor;
import org.otus.education.jdbc.mapper.EntityClassMetaData;
import org.otus.education.jdbc.mapper.EntitySQLMetaData;
import org.otus.education.jdbc.mapper.JdbcMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;
    private final DbExecutor<T> dbExecutor;
    private final Connection connection;

    private JdbcMapperImpl() {
        this(null, null, null, null);
    }

    private JdbcMapperImpl(EntityClassMetaData<T> entityClassMetaData, EntitySQLMetaData entitySQLMetaData, DbExecutor<T> dbExecutor, Connection connection) {
        this.entityClassMetaData = entityClassMetaData;
        this.entitySQLMetaData = entitySQLMetaData;
        this.dbExecutor = dbExecutor;
        this.connection = connection;
    }

    @SneakyThrows
    @Override
    public long insert(T objectData) {
        List<Object> params = entityClassMetaData.getAllFields().stream().map(x -> getFieldValue(x, objectData)).collect(Collectors.toList());
        return dbExecutor.executeInsert(connection, entitySQLMetaData.getInsertSql(), params);
    }

    @SneakyThrows
    @Override
    public void update(T objectData) {
        List<Object> params = Stream.concat(entityClassMetaData.getFieldsWithoutId().stream(),
                Stream.of(entityClassMetaData.getIdField()))
                .map(field -> getFieldValue(field, objectData))
                .collect(Collectors.toList());
        dbExecutor.executeInsert(connection, entitySQLMetaData.getUpdateSql(), params);
    }

    @SneakyThrows
    @Override
    public void insertOrUpdate(T objectData) {
        final Object id = entityClassMetaData.getIdField().get(objectData);
        if (findById(id).isPresent()) {
            update(objectData);
        } else {
            insert(objectData);
        }
    }

    @SneakyThrows
    @Override
    public Optional<T> findById(Object id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), id, this::getRowById);
    }

    @SneakyThrows
    private T getRowById(ResultSet resultSet) {
        if (resultSet.next()) {
            Constructor<T> constructor = entityClassMetaData.getConstructor();
            T result = constructor.newInstance();
            entityClassMetaData.getAllFields().forEach(field -> {
                String fieldName = field.getName();
                field.set(result, resultSet.getObject(fieldName));
            });
            return result;
        }
        return null;
    }


    @SneakyThrows
    private Object getFieldValue(Field field, Object object) {
        return field.get(object);
    }

    public static class Builder {
        private EntityClassMetaData<?> entityClassMetaData;
        private EntitySQLMetaData entitySQLMetaData;
        private DbExecutor<?> dbExecutor;
        private Connection connection;

        public Builder() {
        }

        public Builder connection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder setEntityClassMetaData(EntityClassMetaData<?> entityClassMetaData) {
            this.entityClassMetaData = entityClassMetaData;
            return this;
        }

        public Builder setEntitySQLMetaData(EntitySQLMetaData entitySQLMetaData) {
            this.entitySQLMetaData = entitySQLMetaData;
            return this;
        }

        public Builder setDbExecutor(DbExecutor<?> dbExecutor) {
            this.dbExecutor = dbExecutor;
            return this;
        }

        public JdbcMapper build() {
            if (Objects.isNull(connection)) {
                throw new NullPointerException("Connection is null");
            }
            if (Objects.isNull(dbExecutor)) {
                throw new NullPointerException("DataBase Executor is null");
            }
            if (Objects.isNull(entityClassMetaData)) {
                throw new NullPointerException("EntityClassMetaData is null");
            }
            if (Objects.isNull(entitySQLMetaData)) {
                throw new NullPointerException("EntitySQLMetaData is null");
            }

            return new JdbcMapperImpl(entityClassMetaData, entitySQLMetaData, dbExecutor, connection);
        }
    }
}
