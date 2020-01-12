package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, gmt_create, gmt_modify, creator, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> getQuestion(@Param("offset") Integer offset,
                               @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(Integer userId);

    @Select("select * from question where creator = #{userId} limit #{offset}, #{size}")
    List<Question> getQuestionByUser(@Param("userId") Integer userId,
                                     @Param("offset") Integer offset,
                                     @Param("size") Integer size);

    @Select("select * from question where id = #{questionId}")
    Question getQuestionByQuestionId(Integer questionId);

    @Update("update question set title = #{title}, description = #{description}, gmt_modify = #{gmtModify}, tag = #{tag} where id = #{id}")
    boolean update(Question question);

    @Update("update question set view_count = view_count + 1 where id = #{questionId}")
    void increaseViewByQuestionId(Integer questionId);
}
