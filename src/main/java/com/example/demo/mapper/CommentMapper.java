package com.example.demo.mapper;

import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {


    @Insert("insert into comment (parent_id, parent_type, commentator, gmt_create, gmt_modify, content) values (#{parentId}, #{parentType}, #{commentator}, #{gmtCreate}, #{gmtModify}, #{content})")
    void insert(Comment comment);
}
