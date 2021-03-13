package com.af.blog.vo;

import lombok.Data;


/**
 * 返回信息包装类
 * @param <T>
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
