package com.af.blog.utils;

import com.af.blog.vo.ResultVO;
import com.af.blog.constants.ResultCode;
import com.af.blog.constants.StatusCode;

/**
 * 返回信息工具类
 */
public class ResultVoUtils {

    /**
     * 返回一个空的成功请求
     * @return
     */
    public static ResultVO<Object> success() {
        return success(null);
    }

    /**
     * 返回带信息的成功请求
     * @param object
     * @return
     */
    public static ResultVO<Object> success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCode.SUCCESS_CODE.getCode());
        resultVO.setMsg(ResultCode.SUCCESS_CODE.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 返回默认错误请求
     * @return 请求失败 code: 1001
     */
    public static ResultVO<Object> error() {
        return error(ResultCode.FAILED_CODE, null);
    }

    /**
     * 返回错误请求
     * @param statusCode 错误码
     * @param o 详细错误信息
     * @return
     */
    public static ResultVO<Object> error(StatusCode statusCode, Object o) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(statusCode.getCode());
        resultVO.setMsg(statusCode.getMsg());
        resultVO.setData(o);
        return  resultVO;
    }
}
