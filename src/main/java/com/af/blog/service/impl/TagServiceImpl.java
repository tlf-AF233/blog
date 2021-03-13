package com.af.blog.service.impl;


import com.af.blog.entity.Tag;
import com.af.blog.mapper.TagMapper;
import com.af.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public boolean createTag(String tagName) {
        return tagMapper.createTag(tagName);
    }

    @Override
    public List<Tag> selectAllTags() {
        return tagMapper.selectAllTags();
    }

    @Override
    public Tag selectTagById(Integer tagId) {
        return tagMapper.selectTagById(tagId);
    }

    @Override
    public boolean updateTagById(Tag tag) {
        return tagMapper.updateTagById(tag);
    }

    @Override
    public boolean isExist(String tagName) {
        return tagMapper.isExist(tagName) == 1;
    }

    @Override
    public Integer selectCounts() {
        return tagMapper.selectCounts();
    }
}
