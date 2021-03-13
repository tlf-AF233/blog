package com.af.blog.mapper;


import com.af.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    // 评论
    boolean createComment(Comment comment);

    // 留言
    boolean createMessage(Comment comment);

    // 查询评论（父节点）
    List<Comment> selectCommentByBlogId(@Param("blogId") Integer blogId);

    // 查询留言（父节点）
    List<Comment> selectMessage();

    boolean deleteCommentById(@Param("commentId") Integer commentId);
}
