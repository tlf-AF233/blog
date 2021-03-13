package com.af.blog.handler;


import com.af.blog.constants.ResultCode;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", ResultVoUtils.error(ResultCode.FAILED_CODE, e));
        mv.setViewName("error/error");
        return mv;
    }
}
