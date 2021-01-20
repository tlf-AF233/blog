package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 回复表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    /**
     * 主键
     */
    private Integer replyId;

    /**
     * 回复的评论
     */
    private Integer replyCom;

    /**
     * 被回复的用户
     */
    private Integer replyTo;

    /**
     * 发表回复的用户
     */
    private Integer replyFrom;

    /**
     * 回复的内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    private Date createTime;
}
