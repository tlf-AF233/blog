package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 评论表
 */
@Builder
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
     * 评论用户Id
     */
    private Integer comFrom;

    /**
     * 评论用户昵称
     */
    private String userNickname;

    /**
     * 评论用户头像
     */
    private String userAvatar;

    /**
     * 评论用户的角色
     */
    private String roleName;

    /**
     * 评论用户的邮箱
     */
    private String userEmail;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 子节点集合
     */
    private List<Comment> childList;

    /**
     * 父节点
     */
    private Integer parentComId;

    private String parentName;

    /**
     * 逻辑删除
     */
    private Integer deleted;
}
