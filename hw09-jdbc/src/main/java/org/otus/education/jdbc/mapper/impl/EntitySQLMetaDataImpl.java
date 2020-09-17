package org.otus.education.jdbc.mapper.impl;

import org.otus.education.jdbc.mapper.EntityClassMetaData;
import org.otus.education.jdbc.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final String selectAll;
    private final String selectById;
    private final String insert;
    private final String update;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> metaData) {
        final String columns = metaData.getAllFields()
                .stream()
                .map(Field::getName)
                .collect(Collectors.joining(", "));
        final String table = metaData.getName();

        final String columnsParameters = generateParameterString(metaData.getAllFields().size());
        final String columnForUpdate = metaData.getFieldsWithoutId()
                .stream()
                .map(map -> String.format("%s = ?", map.getName()))
                .collect(Collectors.joining(", "));
        final String id = metaData.getIdField().getName();

        this.selectAll = String.format("select %s from %s",
                columns,
                table
        );
        this.selectById = String.format("select %s from %s where %s = ?",
                columns,
                table,
                id
        );
        this.insert = String.format("insert into %s (%s) values (%s)",
                table,
                columns,
                columnsParameters
        );
        this.update = String.format("update %s set %s where %s = ?",
                table,
                columnForUpdate,
                id);
    }

    public String generateParameterString(int size) {
        return "? ".repeat(size).trim().replace(" ",", ");
    }

    @Override
    public String getSelectAllSql() {
        return selectAll;
    }

    @Override
    public String getSelectByIdSql() {
        return selectById;
    }

    @Override
    public String getInsertSql() {
        return insert;
    }

    @Override
    public String getUpdateSql() {
        return update;
    }
}
