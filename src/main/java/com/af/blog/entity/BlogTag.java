package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 博客和标签关联表 “多对多关系”
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTag {

    /**
     * 主键
     */
    private Integer btId;

    /**
     * 标签主键
     */
    private Integer btTagId;

    /**
     * 博客主键
     */
    private Integer btBlogId;
}
