package org.otus.education.jdbc.mapper.impl;

import org.otus.education.annotation.dbfield.Id;
import org.otus.education.annotation.dbfield.IdAnnotationException;
import org.otus.education.jdbc.mapper.EntityClassMetaData;
import org.otus.education.jdbc.exception.EntityClassMetaDataException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;
    private final Constructor<T> constructor;
    private final Field idField;
    private final List<Field> fields;
    private final List<Field> fieldListWithOutId;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.constructor = findConstructor();
        this.fields=  getFieldList(clazz);
        this.idField = findIdField();
        this.fieldListWithOutId = fields.stream().filter(Predicate.not(idField::equals)).collect(Collectors.toUnmodifiableList());
    }

    private List<Field> getFieldList(Class<T> clazz) {
        final List<Field> fields = List.of(clazz.getDeclaredFields());
        fields.forEach(x -> x.setAccessible(true));
        return fields;
    }


    private Constructor<T> findConstructor() {
        try {
            final Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException e) {
           throw new EntityClassMetaDataException("Constructor not found");
        }
    }

    private Field findIdField() {
        List<Field> idFieldList = fields.stream()
                .filter(field -> field.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());
        if (idFieldList.isEmpty()) {
            throw new IdAnnotationException("In class " + clazz.getCanonicalName() + " annotation @Id not found");
        }
        if (idFieldList.size() > 1) {
            throw new IdAnnotationException("In class " + clazz.getCanonicalName() + "Annotation @Id found more than 1");
        }
        return idFieldList.get(0);
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldListWithOutId;
    }
}
