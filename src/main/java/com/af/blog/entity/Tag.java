package com.af.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    /**
     * 主键
     */
    private Integer tagId;

    /**
     * 标签名
     */
    private String tagName;
}
