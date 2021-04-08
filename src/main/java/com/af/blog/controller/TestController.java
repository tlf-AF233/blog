package com.af.blog.controller;


import com.af.blog.service.BlogService;
import com.af.blog.service.CommentService;
import com.af.blog.service.TagService;
import com.af.blog.service.TypeService;
import com.af.blog.utils.FileUtils;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadMarkdownImg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        String picture = FileUtils.upload(attach, "picture");
        Map<String, Object> map = new HashMap<>();
        map.put("url", "http://img.afblog.love/" + picture);
        map.put("success", 1);
        map.put("message", "上传成功");
        return map;
    }
}
