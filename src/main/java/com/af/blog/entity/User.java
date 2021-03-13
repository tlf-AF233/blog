package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 激活状态：1（激活） 0（未激活）
     */
    private Integer userStatus;

    /**
     * 验证码
     */
    private String userCode;

    /**
     * 角色
     */
    private Integer userRole;

    private String roleName;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer deleted;
}
