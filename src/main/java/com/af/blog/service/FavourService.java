package com.af.blog.service;

import org.apache.ibatis.annotations.Param;

/**
 * @author AF
 * @date 2021/4/8 16:51
 */
public interface FavourService {

    /**
     * 是否点赞过
     * @param userId
     * @param blogId
     * @return 0 为没有 1 为已点赞
     */
    boolean isFavoured(Integer userId, Integer blogId);

    /**
     * 点赞
     * @param userId
     * @param blogId
     * @return
     */
    boolean createFavour(Integer userId, Integer blogId);

    /**
     * 取消赞
     * @param userId
     * @param blogId
     * @return
     */
    boolean cancelFavour(Integer userId, Integer blogId);
}
