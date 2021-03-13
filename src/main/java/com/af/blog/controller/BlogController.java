package com.af.blog.controller;


import com.af.blog.entity.Blog;
import com.af.blog.entity.Tag;
import com.af.blog.service.*;
import com.af.blog.utils.ResultVoUtils;
import com.af.blog.vo.BlogVO;
import com.af.blog.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogVOService blogVOService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;


    /**
     * 查找所有博客 并重定向到blog.html
     * @param pageNum 页数 默认一页展示6条博客
     */
    @GetMapping(value = "/blogs/{pageNum}")
    public String selectAllBlogsToShow(@RequestParam(value = "typeId", required = false) Integer typeId,
                                       @RequestParam(value = "blogTitle", required = false) String blogTitle,
                                       @PathVariable("pageNum") Integer pageNum,
                                       Model model) {
        PageHelper.startPage(pageNum, 6);
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOService.selectAllBlogsToShow(typeId, blogTitle));
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("types", typeService.selectAllTypes());
        resultMap.put("tags", tagService.selectAllTags());
        model.addAttribute("blogInfo", resultMap);
        return "blog";
    }

    /*@GetMapping("/blogs/{pageNum}")
    public ResultVO<Object> selectAllBlogs(@PathVariable("pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        return ResultVoUtils.success(new PageInfo<Blog>(blogService.selectAllBlogs()));
    }*/

    /**
     * 查看博客详情
     * @param blogId Id
     * @param model
     * @return
     */
    @GetMapping("/article/{blogId}")
    public String blogDetail(@PathVariable("blogId") Integer blogId, Model model) {
        model.addAttribute("blog", blogVOService.getAndConvert(blogId));
        model.addAttribute("author", blogVOService.selectAuthor(blogId));
        model.addAttribute("comments", commentService.selectCommentByBlogId(blogId));
        return "blogdetail";
    }
}
