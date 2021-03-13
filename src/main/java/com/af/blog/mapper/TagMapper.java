package com.af.blog.mapper;

import com.af.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    boolean createTag(@Param("tagName") String tagName);

    List<Tag> selectAllTags();

    Tag selectTagById(@Param("tagId") Integer tagId);

    boolean updateTagById(Tag tag);

    Integer isExist(@Param("tagName") String tagName);

    Integer selectCounts();
}
