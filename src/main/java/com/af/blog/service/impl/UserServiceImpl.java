package com.af.blog.service.impl;


import com.af.blog.entity.User;
import com.af.blog.mapper.UserMapper;
import com.af.blog.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id查找用户
     *
     * @param userId id
     */
    @Override
    public User selectAnUserById(Integer userId) {
        return userMapper.selectAnUserById(userId);
    }

    /**
     * 判断用户名是否存在
     * @param userName
     * @return
     */
    @Override
    public boolean isExist(String userName) {
        return userMapper.isExist(userName) == 1;
    }

    /**
     * 判断邮箱是否存在且激活过
     * @param userEmail
     * @return
     */
    @Override
    public boolean isEmailExist(String userEmail) {
        return userMapper.isEmailExist(userEmail) == 1;
    }

    /**
     * 注册时激活邮箱
     * @param user
     * @return
     */
    @Override
    public boolean checkEmail(User user) {
        return userMapper.checkEmail(user);
    }

    /**
     * 获取数据库中存储的用户激活码
     * @param userId
     * @return
     */
    @Override
    public String getActiveCode(Integer userId) {
        return userMapper.getActiveCode(userId);
    }

    /**
     * 激活用户，更改状态，置空激活码
     * @param userId
     * @return
     */
    @Override
    public boolean activeUser(Integer userId) {
        return userMapper.activeUser(userId);
    }

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    @Override
    public User selectAnUserByName(String userName) {
        return userMapper.selectAnUserByName(userName);
    }

    /**
     * 查询用户详细信息
     * @param userId
     * @return
     */
    @Override
    public User selectAnUserDetailById(Integer userId) {
        return userMapper.selectAnUserDetailById(userId);
    }

    /**
     * 生成一个匿名用户 (用户昵称，邮箱)
     * @param user
     * @return
     */
    @Override
    public boolean createAnAnonUser(User user) {
        return userMapper.createAnAnonUser(user);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean createAnUser(User user) {
        // 对注册密码进行加密
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword());
        user.setUserPassword(md5Hash.toHex());
        return userMapper.createAnUser(user);
    }

    /**
     * 修改密码时检查密码是否正确
     * @param userId
     * @param oldPwd 旧密码
     * @return
     */
    @Override
    public boolean checkPassword(Integer userId, String oldPwd) {
        // 对密码加密
        Md5Hash md5Hash = new Md5Hash(oldPwd);
        return userMapper.checkPassword(userId, md5Hash.toHex()) == 1;
    }

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        if (user.getUserPassword() != null) {
            // 加密
            Md5Hash md5Hash = new Md5Hash(user.getUserPassword());
            user.setUserPassword(md5Hash.toHex());
        }
        return userMapper.updateUser(user);
    }

    /**
     * 查询所有注册用户
     * @return
     */
    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    /**
     * 后台更改用户认证状态
     * @param userId
     * @param userStatus
     * @return
     */
    @Override
    public boolean changeUserStatus(Integer userId, Integer userStatus) {
        return userMapper.changeUserStatus(userId, userStatus);
    }

    /**
     * 逻辑删除用户
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUserById(Integer userId) {
        return userMapper.deleteUserById(userId);
    }
}
