package com.af.blog.service;

import com.af.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    boolean createComment(Comment comment);

    boolean createMessage(Comment comment);

    List<Comment> selectCommentByBlogId(Integer blogId);

    List<Comment> selectMessage();

    boolean deleteCommentById(Integer commentId);
}
