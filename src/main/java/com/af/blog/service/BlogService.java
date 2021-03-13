package com.af.blog.service;


import com.af.blog.entity.Blog;
import org.springframework.web.multipart.MultipartFile;


public interface BlogService {

    boolean createBlog(Blog blog, MultipartFile file);

    boolean updateBlog(Blog blog, MultipartFile file);

    boolean changeBlogPublish(Integer blogId, Integer blogPublish);

    Integer selectCounts();
}
