package org.otus.education.jdbc.mapper.impl;

import org.otus.education.jdbc.DbExecutor;
import org.otus.education.jdbc.mapper.EntityClassMetaData;
import org.otus.education.jdbc.mapper.EntitySQLMetaData;
import org.otus.education.jdbc.mapper.JdbcMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private final EntityClassMetaData<T> entityClassMetaData;
    private final EntitySQLMetaData entitySQLMetaData;
    private final DbExecutor<T> dbExecutor;

    private JdbcMapperImpl() {
        this(null, null, null);
    }

    public JdbcMapperImpl(EntityClassMetaData<T> entityClassMetaData, EntitySQLMetaData entitySQLMetaData, DbExecutor<T> dbExecutor) {
        this.entityClassMetaData = entityClassMetaData;
        this.entitySQLMetaData = entitySQLMetaData;
        this.dbExecutor = dbExecutor;
    }


    @Override
    public long insert(T objectData, Connection connection) {
        List<Object> params = entityClassMetaData.getAllFields().stream().map(x -> getFieldValue(x, objectData)).collect(Collectors.toList());
        try {
            return dbExecutor.executeInsert(connection, entitySQLMetaData.getInsertSql(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }


    @Override
    public void update(T objectData, Connection connection) {
        List<Object> params = Stream.concat(entityClassMetaData.getFieldsWithoutId().stream(),
                Stream.of(entityClassMetaData.getIdField()))
                .map(field -> getFieldValue(field, objectData))
                .collect(Collectors.toList());
        try {
            dbExecutor.executeInsert(connection, entitySQLMetaData.getUpdateSql(), params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(T objectData, Connection connection) {
        Object id  = null;
        try {
            id = entityClassMetaData.getIdField().get(objectData);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (findById(id, connection).isPresent()) {
            update(objectData, connection);
        } else {
            insert(objectData, connection);
        }
    }


    @Override
    public Optional<T> findById(Object id, Connection connection) {
        try {
            return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), id, this::getRowById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    private T getRowById(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Constructor<T> constructor = entityClassMetaData.getConstructor();
                T result = constructor.newInstance();
                entityClassMetaData.getAllFields().forEach(field -> {
                    String fieldName = field.getName();
                    try {
                        field.set(result, resultSet.getObject(fieldName));
                    } catch (IllegalAccessException | SQLException e) {
                        e.printStackTrace();
                    }
                });
                return result;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private Object getFieldValue(Field field, Object object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
