package com.af.blog.controller;


import com.af.blog.service.BlogService;
import com.af.blog.service.CommentService;
import com.af.blog.service.TagService;
import com.af.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;


    @RequestMapping("/message")
    public String toMessage(Model model) {
        model.addAttribute("messages", commentService.selectMessage());
        return "message";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/about")
    public String toAbout(Model model) {
        model.addAttribute("tagNum", tagService.selectCounts());
        model.addAttribute("typeNum", typeService.selectCounts());
        model.addAttribute("blogNum", blogService.selectCounts());
        return "aboutme";
    }
}
