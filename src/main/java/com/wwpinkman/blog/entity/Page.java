package com.wwpinkman.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Page {
  private Integer pageId;

  private String pageKey;

  private String pageTitle;

  private String pageContent;

  private Date pageCreateTime;

  private Date pageUpdateTime;

  private Integer pageViewCount;

  private Integer pageCommentCount;

  private Integer pageStatus;
}
