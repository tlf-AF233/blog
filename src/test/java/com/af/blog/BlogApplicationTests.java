package com.af.blog;

import com.af.blog.entity.Comment;
import com.af.blog.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private CommentService commentService;
    @Test
    void contextLoads() {
        Comment comment = new Comment();
        List<Comment> comments = commentService.selectCommentByBlogId(2);
        for (Comment comment1 : comments) {
            System.out.println(comment1.getChildList());
        }

    }

}
