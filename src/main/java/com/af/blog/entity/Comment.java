package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    /**
     * 主键
     */
    private Integer comId;

    /**
     * 评论的博客
     */
    private Integer comTo;

    /**
     * 评论内容
     */
    private String comContent;

    /**
     * 评论用户
     */
    private Integer comFrom;

    /**
     * 评论时间
     */
    private Date createTime;
}
