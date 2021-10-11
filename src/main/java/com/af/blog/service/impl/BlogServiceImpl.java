package com.af.blog.service.impl;


import com.af.blog.constants.RedisConstants;
import com.af.blog.entity.Blog;
import com.af.blog.mapper.BlogMapper;
import com.af.blog.service.BlogService;
import com.af.blog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean createBlog(Blog blog, MultipartFile file) {
        blog.setBlogPicture(FileUtils.upload(file, "picture"));
        return blogMapper.createBlog(blog);
    }

    @Override
    public boolean updateBlog(Blog blog, MultipartFile file) {
        if (file == null)
            blog.setBlogPicture(null);
        else
            blog.setBlogPicture(FileUtils.upload(file, "picture"));
        return blogMapper.updateBlog(blog);
    }

    @Override
    public boolean changeBlogPublish(Integer blogId, Integer blogPublish) {
        return blogMapper.changeBlogPublish(blogId, blogPublish);
    }

    @Override
    public Integer selectCounts() {
        return blogMapper.selectCounts();
    }

    @Override
    public boolean updateView(String ipAddress, Integer blogId) {
        if (addPv(ipAddress, blogId)) {
            return blogMapper.updateView(blogId);
        }
        return false;
    }

    @SuppressWarnings("all")
    private boolean addPv(String ipAddress, Integer blogId) {
        String hash = buildPvHash(blogId);
        Object time = redisTemplate.opsForHash().get(hash, ipAddress);
        if (time != null && (long) time < LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
            redisTemplate.opsForHash().delete(hash, ipAddress);
            redisTemplate.opsForHash().put(hash, ipAddress, LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());
            return true;
        }
        return redisTemplate.opsForHash().putIfAbsent(hash, ipAddress, LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    private String buildPvHash(Integer blogId) {
        return RedisConstants.PV + ":" + blogId;
    }
}
