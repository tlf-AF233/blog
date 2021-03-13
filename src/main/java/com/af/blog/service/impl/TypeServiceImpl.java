package com.af.blog.service.impl;


import com.af.blog.entity.Type;
import com.af.blog.mapper.TypeMapper;
import com.af.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    @Override
    public List<Type> selectAllTypes() {
        return typeMapper.selectAllTypes();
    }

    @Override
    public Type selectTypeById(Integer typeId) {
        return typeMapper.selectTypeById(typeId);
    }

    @Override
    public boolean updateTypeById(Type type) {
        return typeMapper.updateTypeById(type);
    }

    @Override
    public boolean isExist(String typeName) {
        return typeMapper.isExist(typeName) == 1;
    }

    @Override
    public boolean createType(String typeName) {
        return typeMapper.createType(typeName);
    }

    @Override
    public Integer selectCounts() {
        return typeMapper.selectCounts();
    }
}
