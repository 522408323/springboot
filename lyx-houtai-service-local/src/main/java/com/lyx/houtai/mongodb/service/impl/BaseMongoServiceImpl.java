package com.lyx.houtai.mongodb.service.impl;

import com.lyx.houtai.mongodb.BaseMongoModel;
import com.lyx.houtai.mongodb.service.BaseService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.*;
import java.util.List;

public abstract class BaseMongoServiceImpl<T  extends BaseMongoModel> implements BaseService<T> {

    protected final Class<T> clazz;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void insert(T t) {
        mongoTemplate.insert(t);
    }

    @Override
    public UpdateResult update(T t) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(t.getId()).and("version").is(t.getVersion()));
        Update update = buildUpdate(t);
        return mongoTemplate.updateFirst(query, update, clazz);
    }

    @Override
    public DeleteResult remove(T t) {
        return mongoTemplate.remove(t);
    }

    @Override
    public DeleteResult removeByQuery(Query query) {
        return mongoTemplate.remove(query, clazz);
    }

    @Override
    public UpdateResult updateFirst(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, clazz);
    }

    @Override
    public T queryById(ObjectId id) {
        return mongoTemplate.findById(id, clazz);
    }

    @Override
    public long countByParam(BaseService.BaseParam baseParam) {
        return mongoTemplate.count(baseParam.buildCriteriaQuery(), clazz);
    }

    @Override
    public List<T> queryByParam(BaseParam baseParam) {
        return queryByNativeQuery(baseParam.buildQuery());
    }

    @Override
    public List<T> queryByNativeQuery(Query query) {
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public DeleteResult deleteByParam(BaseParam baseParam) {
        return mongoTemplate.remove(baseParam.buildQuery(), clazz);
    }

    public BaseMongoServiceImpl() {
        clazz = findTypeParam(this, BaseMongoServiceImpl.class, "T");
    }

    public abstract Update buildUpdate(T t);

    @SuppressWarnings("unchecked")
    public static Class findTypeParam(
            final Object object, Class parameterizedSuperclass, String typeParamName) {

        final Class thisClass = object.getClass();
        Class currentClass = thisClass;
        for (; ; ) {
            if (currentClass.getSuperclass() == parameterizedSuperclass) {
                int typeParamIndex = -1;
                TypeVariable<?>[] typeParams = currentClass.getSuperclass().getTypeParameters();
                for (int i = 0; i < typeParams.length; i++) {
                    if (typeParamName.equals(typeParams[i].getName())) {
                        typeParamIndex = i;
                        break;
                    }
                }

                if (typeParamIndex < 0) {
                    throw new IllegalStateException(
                            "unknown type parameter '" + typeParamName + "': " + parameterizedSuperclass);
                }

                Type genericSuperType = currentClass.getGenericSuperclass();
                if (!(genericSuperType instanceof ParameterizedType)) {
                    return Object.class;
                }

                Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                Type actualTypeParam = actualTypeParams[typeParamIndex];
                if (actualTypeParam instanceof ParameterizedType) {
                    actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
                }
                if (actualTypeParam instanceof Class) {
                    return (Class<?>) actualTypeParam;
                }
                if (actualTypeParam instanceof GenericArrayType) {
                    Type componentType = ((GenericArrayType) actualTypeParam).getGenericComponentType();
                    if (componentType instanceof ParameterizedType) {
                        componentType = ((ParameterizedType) componentType).getRawType();
                    }
                    if (componentType instanceof Class) {
                        return Array.newInstance((Class<?>) componentType, 0).getClass();
                    }
                }
                if (actualTypeParam instanceof TypeVariable) {
                    // Resolved type parameter points to another type parameter.
                    TypeVariable<?> v = (TypeVariable<?>) actualTypeParam;
                    currentClass = thisClass;
                    if (!(v.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }

                    parameterizedSuperclass = (Class<?>) v.getGenericDeclaration();
                    typeParamName = v.getName();
                    if (parameterizedSuperclass.isAssignableFrom(thisClass)) {
                        continue;
                    } else {
                        return Object.class;
                    }
                }

                return fail(thisClass, typeParamName);
            }
            currentClass = currentClass.getSuperclass();
            if (currentClass == null) {
                return fail(thisClass, typeParamName);
            }
        }
    }

    private static Class<?> fail(Class<?> type, String typeParamName) {
        throw new IllegalStateException(
                "cannot determine the type of the type parameter '" + typeParamName + "': " + type);
    }
}
