package com.af.blog.mapper;


import com.af.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface BlogTagMapper {

    boolean createBlogTag(@Param("blogId") Integer blogId, @Param("tags") int[] tags);

    boolean deleteBlogTag(@Param("blogId") Integer blogId);
}
