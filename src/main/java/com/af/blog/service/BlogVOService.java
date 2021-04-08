package com.af.blog.service;


import com.af.blog.entity.User;
import com.af.blog.vo.BlogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogVOService {

    List<BlogVO> selectAllBlogsToBack();

    BlogVO selectBlogById(Integer blogId);

    BlogVO getAndConvert(Integer blogId);

    List<BlogVO> selectAllBlogsToShow(Integer typeId, String blogTitle);

    User selectAuthor(Integer blogId);

    boolean deleteBlogById(Integer blogId);

    boolean favourBlog(Integer blogId);

    boolean unFavourBlog(Integer blogId);
}
