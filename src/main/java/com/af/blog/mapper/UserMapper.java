package com.af.blog.mapper;

import com.af.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User selectAnUserById(@Param("userId") Integer userId);

    Integer isExist(@Param("userName") String userName);

    Integer isEmailExist(@Param("userEmail") String userEmail);

    boolean checkEmail(User user);

    List<User> selectAllUsers();

    Integer checkPassword(@Param("userId") Integer userId, @Param("oldPwd") String oldPwd);

    String getActiveCode(Integer userId);

    boolean activeUser(Integer userId);

    User selectAnUserDetailById(Integer userId);

    User selectAnUserByName(@Param("userName") String userName);

    // 创建一个匿名用户
    boolean createAnAnonUser(User user);

    boolean createAnUser(User user);

    boolean updateUser(User user);

    boolean changeUserStatus(@Param("userId") Integer userId, @Param("userStatus") Integer userStatus);

    boolean deleteUserById(@Param("userId") Integer userId);
}
