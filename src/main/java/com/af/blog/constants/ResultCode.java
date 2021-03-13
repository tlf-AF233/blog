package com.af.blog.constants;

/**
 * 状态码枚举类
 */
public enum  ResultCode implements StatusCode{

    SUCCESS_CODE(1000, "请求成功"),
    FAILED_CODE(1001, "请求失败"),
    INVALID_CODE(3001, "参数验证失败"),
    USER_LOGIN_ERROR(2001, "登录失败"),
    FILE_UPLOAD_ERROR(2002, "文件上传失败");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
