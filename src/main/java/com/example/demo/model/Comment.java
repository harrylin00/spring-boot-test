package com.example.demo.model;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer parentType;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModify;
    private String content;
    private Integer likeCount;
}
