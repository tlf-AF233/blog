package com.af.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 点赞表
 * @author AF
 * @date 2021/4/8 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favour {


    private long favourId;

    private Integer userId;

    private Integer favourBlog;

    /**
     * 点赞状态：1表示点赞，0表示取消
     */
    private Integer favourStatus;

    private Date createTime;

    private Date updateTime;
}
