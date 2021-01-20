package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客类型表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    /**
     * 主键
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;
}
