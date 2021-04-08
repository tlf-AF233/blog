package com.af.blog.vo;

import com.af.blog.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO {

    /**
     * 主键
     */
    private Integer blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 简介
     */
    private String blogDescription;

    /**
     * 正文
     */
    private String blogContent;

    /**
     * 作者Id
     */
    private Integer blogAuthor;

    /**
     * 作者用户名
     */
    private String userName;

    /**
     * 作者昵称
     */
    private String userNickname;

    /**
     * 作者头像
     */
    private String userAvatar;

    /**
     * 分类Id
     */
    private Integer blogType;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 封面
     */
    private String blogPicture;

    /**
     * 是否发布
     */
    private Integer blogPublish;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签集合
     */
    private List<Tag> tags;

    /**
     * 点赞量
     */
    private long favourNum;

    /**
     * 逻辑删除
     */
    private Integer deleted;
}
