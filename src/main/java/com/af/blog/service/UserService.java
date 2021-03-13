package com.af.blog.service;

import com.af.blog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据id查找用户
     *
     * @param userId id
     */
    User selectAnUserById(Integer userId);

    /**
     * 判断用户名是否存在
     * @param userName
     * @return
     */
    boolean isExist(String userName);

    /**
     * 判断邮箱是否存在且激活过
     * @param userEmail
     * @return
     */
    boolean isEmailExist(String userEmail);

    /**
     * 注册时激活邮箱
     * @param user
     * @return
     */
    boolean checkEmail(User user);

    /**
     * 修改密码时检查密码是否正确
     * @param userId
     * @param oldPwd 旧密码
     * @return
     */
    boolean checkPassword(Integer userId, String oldPwd);

    /**
     * 查询所有注册用户
     * @return
     */
    List<User> selectAllUsers();

    /**
     * 获取数据库中存储的用户激活码
     * @param userId
     * @return
     */
    String getActiveCode(Integer userId);

    /**
     * 激活用户，更改状态，置空激活码
     * @param userId
     * @return
     */
    boolean activeUser(Integer userId);

    /**
     * 查询用户详细信息
     * @param userId
     * @return
     */
    User selectAnUserDetailById(Integer userId);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    User selectAnUserByName(String userName);

    /**
     * 生成一个匿名用户 (用户昵称，邮箱)
     * @param user
     * @return
     */
    boolean createAnAnonUser(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean createAnUser(User user);

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 后台更改用户认证状态
     * @param userId
     * @param userStatus
     * @return
     */
    boolean changeUserStatus(Integer userId, Integer userStatus);

    /**
     * 逻辑删除用户
     * @param userId
     * @return
     */
    boolean deleteUserById(Integer userId);
}
