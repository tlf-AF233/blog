package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 博客表
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    /**
     * 主键
     */
    private Integer blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 正文
     */
    private String blogContent;

    /**
     * 作者
     */
    private Integer blogAuthor;

    /**
     * 分类
     */
    private Integer blogType;

    /**
     * 封面
     */
    private String blogPicture;

    /**
     * 是否发布
     */
    private Integer blogPublish;

    /**
     * 博客简介
     */
    private String blogDescription;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
