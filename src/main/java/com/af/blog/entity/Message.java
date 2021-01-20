package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 留言表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /**
     * 主键
     */
    private Integer messId;

    /**
     * 留言内容
     */
    private String messContent;

    /**
     * 留言的用户
     */
    private Integer messFrom;

    /**
     * 留言时间
     */
    private Date createTime;
}
