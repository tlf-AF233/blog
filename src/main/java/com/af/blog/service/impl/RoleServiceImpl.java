package com.af.blog.service.impl;

import com.af.blog.entity.Role;
import com.af.blog.mapper.RoleMapper;
import com.af.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRoles();
    }
}
