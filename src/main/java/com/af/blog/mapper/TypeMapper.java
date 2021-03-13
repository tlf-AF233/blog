package com.af.blog.mapper;


import com.af.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    List<Type> selectAllTypes();

    Type selectTypeById(@Param("typeId") Integer typeId);

    boolean updateTypeById(Type type);

    Integer isExist(@Param("typeName") String typeName);

    boolean createType(@Param("typeName") String typeName);

    Integer selectCounts();
}
