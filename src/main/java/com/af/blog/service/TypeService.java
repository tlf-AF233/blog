package com.af.blog.service;

import com.af.blog.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> selectAllTypes();

    Type selectTypeById(Integer typeId);

    boolean updateTypeById(Type type);

    boolean isExist(String typeName);

    boolean createType(String typeName);

    Integer selectCounts();
}
