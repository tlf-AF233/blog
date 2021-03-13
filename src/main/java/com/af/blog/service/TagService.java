package com.af.blog.service;


import com.af.blog.entity.Tag;


import java.util.List;

public interface TagService {

    boolean createTag(String tagName);

    List<Tag> selectAllTags();

    Tag selectTagById(Integer tagId);

    boolean updateTagById(Tag tag);

    boolean isExist(String tagName);

    Integer selectCounts();
}
