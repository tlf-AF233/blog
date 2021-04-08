package com.af.blog.service.impl;


import com.af.blog.entity.User;
import com.af.blog.mapper.BlogVOMapper;
import com.af.blog.service.BlogVOService;
import com.af.blog.utils.MarkdownUtils;
import com.af.blog.vo.BlogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogVOServiceImpl implements BlogVOService {

    @Autowired
    private BlogVOMapper blogVOMapper;

    @Override
    public List<BlogVO> selectAllBlogsToBack() {
        return blogVOMapper.selectAllBlogsToBack();
    }

    @Override
    public List<BlogVO> selectAllBlogsToShow(Integer typeId, String blogTitle) {
        return blogVOMapper.selectAllBlogsToShow(typeId, blogTitle);
    }

    @Override
    public BlogVO selectBlogById(Integer blogId) {
        return blogVOMapper.selectBlogById(blogId);
    }

    @Override
    public BlogVO getAndConvert(Integer blogId) {
        BlogVO blog = blogVOMapper.selectBlogById(blogId);
        String content = blog.getBlogContent();
        String htmlContent = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setBlogContent(htmlContent);
        return blog;
    }

    @Override
    public boolean deleteBlogById(Integer blogId) {
        return blogVOMapper.deleteBlogById(blogId);
    }

    @Override
    public User selectAuthor(Integer blogId) {
        return blogVOMapper.selectAuthor(blogId);
    }

    @Override
    public boolean favourBlog(Integer blogId) {
        return blogVOMapper.favourBlog(blogId);
    }

    @Override
    public boolean unFavourBlog(Integer blogId) {
        return blogVOMapper.unFavourBlog(blogId);
    }
}
