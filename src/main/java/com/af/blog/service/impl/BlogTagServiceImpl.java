package com.af.blog.service.impl;

import com.af.blog.entity.BlogTag;
import com.af.blog.entity.Tag;
import com.af.blog.mapper.BlogTagMapper;
import com.af.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public boolean createBlogTag(Integer blogId, int[] tags) {
        return blogTagMapper.createBlogTag(blogId, tags);
    }

    @Override
    public boolean deleteBlogTag(Integer blogId) {
        return blogTagMapper.deleteBlogTag(blogId);
    }
}
