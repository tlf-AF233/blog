package com.af.blog.mapper;

import com.af.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    List<Role> selectAllRoles();
}
