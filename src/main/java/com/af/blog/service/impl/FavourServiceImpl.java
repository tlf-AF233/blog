package com.af.blog.service.impl;

import com.af.blog.mapper.FavourMapper;
import com.af.blog.service.FavourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AF
 * @date 2021/4/8 16:51
 */
@Service
public class FavourServiceImpl implements FavourService {

    @Autowired
    private FavourMapper favourMapper;

    @Override
    public boolean isFavoured(Integer userId, Integer blogId) {
        return favourMapper.isFavoured(userId, blogId) == 1;
    }

    @Override
    public boolean createFavour(Integer userId, Integer blogId) {
        return favourMapper.createFavour(userId, blogId);
    }

    @Override
    public boolean cancelFavour(Integer userId, Integer blogId) {
        return favourMapper.cancelFavour(userId, blogId);
    }
}
