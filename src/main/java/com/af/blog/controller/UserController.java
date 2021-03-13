package com.af.blog.controller;


import com.af.blog.constants.ResultCode;
import com.af.blog.entity.User;
import com.af.blog.service.UserService;
import com.af.blog.utils.FileUtils;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    @ResponseBody
    public ResultVO<Object> selectUserById(@PathVariable("id") Integer id) {
        return ResultVoUtils.success(userService.selectAnUserById(id));
    }

    /**
     * 查看个人主页
     * @param model
     * @return
     */
    @GetMapping("/space")
    public String selectUserDetailById(Model model) {
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        model.addAttribute("user", userService.selectAnUserDetailById(currentUser.getUserId()));
        return "userdetail";
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ResultVO<Object> updateUser(@RequestBody User user) {
        return ResultVoUtils.success(userService.updateUser(user));
    }

    /**
     * 修改用户密码
     * @param userId
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return
     */
    @PostMapping("/user/updatePwd")
    @ResponseBody
    public ResultVO<Object> updatePwd(@RequestParam("userId") Integer userId,
                                      @RequestParam("oldPwd") String oldPwd,
                                      @RequestParam("newPwd") String newPwd) {
        if (!userService.checkPassword(userId, oldPwd)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "旧密码输入错误！");
        } else {
            User user = User.builder()
                    .userId(userId)
                    .userPassword(newPwd)
                    .build();
            return ResultVoUtils.success(userService.updateUser(user));
        }
    }

    /**
     * 上传头像
     * @param userId
     * @param multipartFile
     * @return
     */
    @PostMapping("/user/avatar")
    @ResponseBody
    public ResultVO<Object> uploadFile(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile multipartFile) {
        String userAvatar = FileUtils.upload(multipartFile, "avatar");
        User user = User.builder()
                .userId(userId)
                .userAvatar(userAvatar)
                .build();
        return ResultVoUtils.success(userService.updateUser(user));
    }
}
