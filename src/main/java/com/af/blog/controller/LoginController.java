package com.af.blog.controller;


import com.af.blog.constants.ResultCode;
import com.af.blog.constants.StatusCode;
import com.af.blog.entity.User;
import com.af.blog.service.MailService;
import com.af.blog.service.UserService;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.utils.UUIDUtils;
import com.af.blog.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/toLogin")
    @ResponseBody
    public ResultVO<Object> checkLogin(@RequestParam("username") String userName, @RequestParam("pwd") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            User currentUser = userService.selectAnUserByName(userName);
            subject.login(token);
            SecurityUtils.getSubject().getSession().setAttribute("loginUser", currentUser);
            return ResultVoUtils.success();
        } catch (Exception e) {
            return ResultVoUtils.error(ResultCode.USER_LOGIN_ERROR, "用户名或者密码错误！");
        }
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResultVO<Object> loginOut() {
        SecurityUtils.getSubject().logout();
        return ResultVoUtils.success();
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    /**
     * 注册用户第一步
     * @param userName 用户名
     * @param userNickName 昵称
     * @param userPassword 密码
     * @return
     */
    @PostMapping("/toRegister")
    @ResponseBody
    public ResultVO<Object> checkRegister(@RequestParam("username") String userName,
                                          @RequestParam("nickname") String userNickName,
                                          @RequestParam("pwd") String userPassword) {
        if (userService.isExist(userName)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "用户名已经存在！");
        } else {
            User user = User.builder()
                        .userName(userName)
                        .userAvatar("avatar/default.jpg")
                        .userNickname(userNickName)
                        .userPassword(userPassword)
                    .build();
            userService.createAnUser(user);
            return ResultVoUtils.success(userService.selectAnUserById(user.getUserId()));
        }
    }

    /**
     * 验证邮箱
     * @param email
     * @param userId
     * @param userNickname
     * @return
     */
    @PostMapping("/checkEmail")
    @ResponseBody
    public ResultVO<Object> checkEmail(@RequestParam("email") String email,
                                       @RequestParam("userId") Integer userId,
                                       @RequestParam("userNickname") String userNickname) {
        if (userService.isEmailExist(email)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "该邮箱已经激活！");
        } else {
            String active_code = UUIDUtils.getUUID();
            User user = User.builder()
                    .userEmail(email)
                    .userCode(active_code)
                    .userId(userId)
                    .build();
            userService.checkEmail(user);
            String text = "<h2>尊敬的用户" + userNickname + "，您好</h2><p>该邮件为系统自动发送，请勿回复。<a href='https://afblog.love/active?user_id="+userId+"&active_code="+active_code+"&nickname="+userNickname+"'>点击此链接激活邮箱</a>，祝您生活愉快！</p>";
            mailService.sendEmail(text, email);
            return ResultVoUtils.success();
        }
    }

    /**
     * 激活用户
     * @param activeCode
     * @param userId
     * @param nickname
     * @return
     */
    @GetMapping("/active")
    public ModelAndView activeEmail(@RequestParam("active_code") String activeCode, @RequestParam("user_id") Integer userId, @RequestParam("nickname") String nickname) {
        ModelAndView mv = new ModelAndView();
        if (activeCode.equals(userService.getActiveCode(userId))) {
            userService.activeUser(userId);
            mv.setViewName("validated_success");
            mv.addObject("userNickname", nickname);
        } else {
            mv.addObject("result", ResultVoUtils.error(ResultCode.FAILED_CODE, "邮箱验证失败"));
            mv.setViewName("error/error");
        }
        return mv;
    }
}
