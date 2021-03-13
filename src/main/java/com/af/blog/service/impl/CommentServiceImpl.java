package com.af.blog.service.impl;


import com.af.blog.entity.Comment;
import com.af.blog.mapper.CommentMapper;
import com.af.blog.mapper.UserMapper;
import com.af.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean createComment(Comment comment) {
        return commentMapper.createComment(comment);
    }

    @Override
    public boolean createMessage(Comment comment) {
        return commentMapper.createMessage(comment);
    }

    /**
     * 查询留言
     * @return
     */
    @Override
    public List<Comment> selectMessage() {
        List<Comment> comments = commentMapper.selectMessage();
        return eachComment(comments);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public boolean deleteCommentById(Integer commentId) {
        return commentMapper.deleteCommentById(commentId);
    }

    /**
     * 取出所有顶级节点（父节点为空）
     * @param blogId
     * @return 处理好的数据
     */
    @Override
    public List<Comment> selectCommentByBlogId(Integer blogId) {
        List<Comment> comments = commentMapper.selectCommentByBlogId(blogId);
        return eachComment(comments);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        // 将评论对象copy到一个新的集合，防止对原有数据库进行修改操作
        for (Comment comment : comments) {
            Comment com = new Comment();
            BeanUtils.copyProperties(comment, com);
            commentsView.add(com);
        }
        // 评论的所有子节点合并为同一级别
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> childList = comment.getChildList();
            for (Comment comment1 : childList) {
                // 循环 找出子节点 放在tempChildren中
                recursively(comment1);
            }
            // 修改顶级节点的child集合为迭代处理后的集合
            comment.setChildList(tempChildren);
            // 清除临时存放区
            tempChildren = new ArrayList<>();
        }
    }
    // 临时存放所有子节点的集合
    private List<Comment> tempChildren = new ArrayList<>();


    private void recursively(Comment comment) {
        tempChildren.add(comment);  // 顶级节点临时添加到存放的集合
        if (comment.getChildList().size() > 0) {
            List<Comment> childList = comment.getChildList();
            for (Comment child : childList) {
                tempChildren.add(child);
                if (child.getChildList().size() > 0) {
                    recursively(child);
                }
            }
        }
    }
}
