package com.af.blog.mapper;


import com.af.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface BlogMapper {

     /**
      * 新增博客
      * @param blog 博客
      */
     boolean createBlog(Blog blog);

     /**
      * 更新
      * @param blog
      * @return
      */
     boolean updateBlog(Blog blog);

     boolean changeBlogPublish(@Param("blogId") Integer blogId, @Param("blogPublish") Integer blogPublish);

     Integer selectCounts();
}
