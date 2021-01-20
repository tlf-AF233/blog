package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * 主键
     */
    private Integer roleId;

    /**
     * 标签名
     */
    private String roleName;
}
