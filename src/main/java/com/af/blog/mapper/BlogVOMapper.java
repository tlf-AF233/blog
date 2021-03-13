package com.af.blog.mapper;


import com.af.blog.entity.User;
import com.af.blog.vo.BlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogVOMapper {

    /**
     * 后台展示
     */
    List<BlogVO> selectAllBlogsToBack();

    /**
     * 逻辑删除
     */
    boolean deleteBlogById(@Param("blogId") Integer blogId);

    /**
     * 根据ID查询博客
     */
    BlogVO selectBlogById(@Param("blogId") Integer blogId);

    /**
     * 查找作者
     * @param blogId
     * @return
     */
    User selectAuthor(@Param("blogId") Integer blogId);

    /**
     * 查询所有博客(返回前台)
     * @return 博客
     */
    List<BlogVO> selectAllBlogsToShow(@Param("typeId") Integer typeId, @Param("blogTitle") String blogTitle);


}
