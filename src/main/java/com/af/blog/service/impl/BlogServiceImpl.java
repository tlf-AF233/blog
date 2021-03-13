package com.af.blog.service.impl;


import com.af.blog.entity.Blog;
import com.af.blog.mapper.BlogMapper;
import com.af.blog.service.BlogService;
import com.af.blog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

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
}
