package com.af.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author AF
 * @date 2021/4/8 16:24
 */
@Mapper
@Repository
public interface FavourMapper {

    Integer isFavoured(@Param("userId") Integer userId, @Param("blogId") Integer blogId);

    boolean createFavour(@Param("userId") Integer userId, @Param("blogId") Integer blogId);

    boolean cancelFavour(@Param("userId") Integer userId, @Param("blogId") Integer blogId);
}
