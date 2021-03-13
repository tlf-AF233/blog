package com.af.blog.controller;


import com.af.blog.constants.ResultCode;
import com.af.blog.entity.Blog;
import com.af.blog.entity.Tag;
import com.af.blog.entity.Type;
import com.af.blog.entity.User;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 后台控制器
 */
@Controller
@RequestMapping("/back")
public class BackController {

    @Autowired
    private BlogVOService blogVOService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    /**
     * 查找所有未删除的博客 5条记录一页
     * @param model
     * @return
     */
    @GetMapping("/blogs/{pageNum}")
    public String selectAllBlogsToBack(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOService.selectAllBlogsToBack());
        model.addAttribute("blogs", pageInfo);
        return "back/backblog";
    }

    /**
     * 查找已注册用户 5条记录一页
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/users/{pageNum}")
    public String selectAllUsers(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        PageInfo<User> pageInfo = new PageInfo<>(userService.selectAllUsers());
        model.addAttribute("users", pageInfo);
        model.addAttribute("roles", roleService.selectAllRoles());
        return "back/backuser";
    }

    /**
     * 查找所有分类 5条记录一页
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/types/{pageNum}")
    public String selectAllTypes(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        PageInfo<Type> pageInfo = new PageInfo<>(typeService.selectAllTypes());
        model.addAttribute("types", pageInfo);
        return "back/backtype";
    }

    /**
     * 查找所有标签，5条记录一页
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/tags/{pageNum}")
    public String selectAllTags(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagService.selectAllTags());
        model.addAttribute("tags", pageInfo);
        return "back/backtag";
    }

    /**
     * 根据Id查找博客
     * @param blogId
     * @return
     */
    @GetMapping("/blog/{blogId}")
    @ResponseBody
    public ResultVO<Object> selectBlogById(@PathVariable("blogId") Integer blogId) {
        return ResultVoUtils.success(blogVOService.selectBlogById(blogId));
    }

    /**
     * 修改用户按钮 查看详情
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResultVO<Object> selectUserDetailById(@PathVariable("userId") Integer userId) {
        return ResultVoUtils.success(userService.selectAnUserDetailById(userId));
    }

    /**
     * 修改分类按钮 查看详情
     * @param typeId
     * @return
     */
    @GetMapping("/type/{typeId}")
    @ResponseBody
    public ResultVO<Object> selectTypeById(@PathVariable("typeId") Integer typeId) {
        return ResultVoUtils.success(typeService.selectTypeById(typeId));
    }

    /**
     * 修改标签按钮 查看详情
     * @param tagId
     * @return
     */
    @GetMapping("/tag/{tagId}")
    @ResponseBody
    public ResultVO<Object> selectTagById(@PathVariable("tagId") Integer tagId) {
        return ResultVoUtils.success(tagService.selectTagById(tagId));
    }

    /**
     * 跳转到更新博客页面
     * @param blogId
     * @param model
     * @return
     */
    @GetMapping("/edit/{blogId}")
    public String editBlog(@PathVariable("blogId") Integer blogId, Model model) {
        model.addAttribute("blogs", blogVOService.selectBlogById(blogId));
        model.addAttribute("types", typeService.selectAllTypes());
        model.addAttribute("tags", tagService.selectAllTags());
        return "back/edit";
    }

    /**
     * 删除博客
     * @param blogId
     * @return
     */
    @DeleteMapping("/blog/{blogId}")
    @ResponseBody
    public ResultVO<Object> deleteBlogById(@PathVariable("blogId") Integer blogId) {
        return ResultVoUtils.success(blogVOService.deleteBlogById(blogId));
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/user/{userId}")
    @ResponseBody
    public ResultVO<Object> deleteUserById(@PathVariable("userId") Integer userId) {
        return ResultVoUtils.success(userService.deleteUserById(userId));
    }

    /**
     * 发布博客页面
     * @param model
     * @return
     */
    @RequestMapping("/publish")
    public String toPublish(Model model) {
        model.addAttribute("types", typeService.selectAllTypes());
        model.addAttribute("tags", tagService.selectAllTags());
        return "back/publish";
    }

    /**
     * 保存/发布 博客
     * @param blogTitle 标题
     * @param blogDescription 描述
     * @param blogPublish 发布
     * @param blogContent 内容
     * @param blogType 类型
     * @param tags 标签
     * @param blogPicture 图片
     */
    @PostMapping("/save")
    @ResponseBody
    public ResultVO<Object> saveBlog(@RequestParam("blogTitle") String blogTitle,
                                     @RequestParam("blogDescription") String blogDescription,
                                     @RequestParam("blogPublish") Integer blogPublish,
                                     @RequestParam("blogContent") String blogContent,
                                     @RequestParam("blogType") Integer blogType,
                                     @RequestParam("tagIds") int[] tags,
                                     @RequestParam("blogPicture") MultipartFile blogPicture) {
        Blog blog = Blog.builder()
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .blogDescription(blogDescription)
                .blogPublish(blogPublish)
                .blogType(blogType)
                .blogAuthor(1)  // TODO 改作者
                .build();
        blogService.createBlog(blog, blogPicture);
        blogTagService.createBlogTag(blog.getBlogId(), tags);

        return ResultVoUtils.success();
    }

    /**
     * 更新博客
     * @param blogTitle
     * @param blogDescription
     * @param blogContent
     * @param blogType
     * @param tags
     * @param blogPicture
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public ResultVO<Object> updateBlog(@RequestParam("blogId") Integer blogId,
                                       @RequestParam(value = "blogTitle", required = false) String blogTitle,
                                       @RequestParam(value = "blogDescription", required = false) String blogDescription,
                                       @RequestParam(value = "blogContent", required = false) String blogContent,
                                       @RequestParam(value = "blogType", required = false) Integer blogType,
                                       @RequestParam(value = "tagIds", required = false) int[] tags,
                                       @RequestParam(value = "blogPicture", required = false) MultipartFile blogPicture) {

        Blog blog = Blog.builder()
                        .blogType(blogType)
                        .blogTitle(blogTitle)
                        .blogDescription(blogDescription)
                        .blogContent(blogContent)
                        .blogId(blogId)
                        .build();
        blogService.updateBlog(blog, blogPicture);
        blogTagService.deleteBlogTag(blogId);
        blogTagService.createBlogTag(blogId, tags);
        return ResultVoUtils.success();
    }

    /**
     * 后台修改用户信息
     * @param userId
     * @param userName
     * @param userNickname
     * @param userEmail
     * @param userRole
     * @return
     */
    @PutMapping("/updateUser")
    @ResponseBody
    public ResultVO<Object> updateUserBack(@RequestParam("userId") Integer userId,
                                           @RequestParam(value = "userName", required = false) String userName,
                                           @RequestParam(value = "userNickname", required = false) String userNickname,
                                           @RequestParam(value = "userEmail", required = false) String userEmail,
                                           @RequestParam(value = "roleId", required = false) Integer userRole) {
        User user = User.builder()
                .userId(userId)
                .userEmail(userEmail)
                .userNickname(userNickname)
                .userName(userName)
                .userRole(userRole)
                .build();
        return ResultVoUtils.success(userService.updateUser(user));
    }

    /**
     * 后台修改分类
     * @param typeId
     * @param typeName
     * @return
     */
    @PutMapping("/updateType")
    @ResponseBody
    public ResultVO<Object> updateType(@RequestParam("typeId") Integer typeId,
                                       @RequestParam(value = "typeName", required = false) String typeName) {
        if (typeService.isExist(typeName)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "该分类名已经存在！");
        }
        Type type = new Type();
        type.setTypeId(typeId);
        type.setTypeName(typeName);
        return ResultVoUtils.success(typeService.updateTypeById(type));
    }

    /**
     * 后台修改标签
     * @param tagId
     * @param tagName
     * @return
     */
    @PutMapping("/updateTag")
    @ResponseBody
    public ResultVO<Object> updateTag(@RequestParam("tagId") Integer tagId,
                                      @RequestParam(value = "tagName", required = false) String tagName) {
        if (tagService.isExist(tagName)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "该标签名已经存在！");
        }
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tag.setTagId(tagId);
        return ResultVoUtils.success(tagService.updateTagById(tag));
    }

    /**
     * 更改博客发布状态
     * @param blogId
     * @param blogPublish
     * @return
     */
    @GetMapping("/change/{blogId}/{blogPublish}")
    @ResponseBody
    public ResultVO<Object> changeBlogPublish(@PathVariable("blogId") Integer blogId, @PathVariable("blogPublish") Integer blogPublish) {
        return ResultVoUtils.success(blogService.changeBlogPublish(blogId, blogPublish));
    }

    /**
     * 后台更改用户认证状态
     * @param userId
     * @param userStatus
     * @return
     */
    @GetMapping("/changeStatus/{userId}/{userStatus}")
    @ResponseBody
    public ResultVO<Object> changeUserStatus(@PathVariable("userId") Integer userId, @PathVariable("userStatus") Integer userStatus) {
        return ResultVoUtils.success(userService.changeUserStatus(userId, userStatus));
    }

    /**
     * 添加分类
     * @param typeName
     * @return
     */
    @PostMapping("/type")
    @ResponseBody
    public ResultVO<Object> createType(@RequestParam("typeName") String typeName) {
        if (typeService.isExist(typeName)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "该分类已存在");
        } else {
            return ResultVoUtils.success(typeService.createType(typeName));
        }
    }

    /**
     * 添加标签
     * @param tagName
     * @return
     */
    @PostMapping("/tag")
    @ResponseBody
    public ResultVO<Object> createTag(@RequestParam("tagName") String tagName) {
        if (tagService.isExist(tagName)) {
            return ResultVoUtils.error(ResultCode.FAILED_CODE, "该标签已经存在");
        } else {
            return ResultVoUtils.success(tagService.createTag(tagName));
        }
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @DeleteMapping("/comment")
    @ResponseBody
    public ResultVO<Object> deleteCommentById(@RequestParam("commentId") Integer commentId) {
        return ResultVoUtils.success(commentService.deleteCommentById(commentId));
    }
}
