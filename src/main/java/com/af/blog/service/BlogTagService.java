package com.af.blog.service;

import com.af.blog.entity.BlogTag;
import com.af.blog.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public interface BlogTagService {

    boolean createBlogTag(Integer blogId, int[] tags);

    boolean deleteBlogTag(Integer blogId);
}
