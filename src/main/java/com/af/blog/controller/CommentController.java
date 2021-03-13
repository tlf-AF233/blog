package com.af.blog.controller;


import com.af.blog.entity.Comment;
import com.af.blog.entity.User;
import com.af.blog.service.BlogVOService;
import com.af.blog.service.CommentService;
import com.af.blog.service.UserService;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogVOService blogVOService;

    @Autowired
    private UserService userService;

    /**
     * 评论一条内容，插入数据库并局部刷新评论区
     * @param model
     * @return
     */
    @PostMapping("/comments")
    public String comments(@RequestParam("comTo") Integer blogId,
                           @RequestParam("comFrom") Integer comFrom,
                           @RequestParam("nickname") String nickname,
                           @RequestParam("parentCommentId") Integer parentCommentId,
                           @RequestParam("email") String email,
                           @RequestParam("content") String content,
                           Model model) {
        if (comFrom == -1) {
            // 如果是匿名用户 则需要先生成一个用户记录
            User user = User.builder()
                    .userEmail(email)
                    .userNickname(nickname)
                    .userAvatar("avatar/anon.jpg")
                    .build();
            userService.createAnAnonUser(user);
            Comment comment = Comment.builder()
                    .comFrom(user.getUserId())
                    .comTo(blogId)
                    .comContent(content)
                    .parentComId(parentCommentId == -1? null: parentCommentId)
                    .build();
            commentService.createComment(comment);
        } else {
            Comment comment = Comment.builder()
                    .comFrom(comFrom)
                    .comTo(blogId)
                    .comContent(content)
                    .parentComId(parentCommentId == -1? null: parentCommentId)
                    .build();
            commentService.createComment(comment);
        }
        model.addAttribute("comments", commentService.selectCommentByBlogId(blogId));
        model.addAttribute("author", blogVOService.selectAuthor(blogId));
        return "blogdetail::commentList";
    }

    /**
     * 留言一条内容，插入数据库并局部刷新留言板
     * @param model
     * @return
     */
    @PostMapping("/messages")
    public String message( @RequestParam("comFrom") Integer comFrom,
                           @RequestParam("nickname") String nickname,
                           @RequestParam("parentCommentId") Integer parentCommentId,
                           @RequestParam("email") String email,
                           @RequestParam("content") String content,
                           Model model) {
        if (comFrom == -1) {
            // 如果是匿名用户 则需要先生成一个用户记录
            User user = User.builder()
                    .userEmail(email)
                    .userNickname(nickname)
                    .userAvatar("avatar/anon.jpg")
                    .build();
            userService.createAnAnonUser(user);
            Comment comment = Comment.builder()
                    .comFrom(user.getUserId())
                    .comContent(content)
                    .parentComId(parentCommentId == -1? null: parentCommentId)
                    .build();
            commentService.createMessage(comment);
        } else {
            Comment comment = Comment.builder()
                    .comFrom(comFrom)
                    .comContent(content)
                    .parentComId(parentCommentId == -1? null: parentCommentId)
                    .build();
            commentService.createMessage(comment);
        }
        model.addAttribute("messages", commentService.selectMessage());
        return "message::messageList";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResultVO<Object> test() {
        return ResultVoUtils.success(commentService.selectCommentByBlogId(2));
    }
}
