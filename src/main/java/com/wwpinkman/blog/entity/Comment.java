package com.wwpinkman.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

  private Integer commentId;

  private Integer commentPid;

  private String commentPname;

  private Integer commentArticleId;

  private String commentAuthorName;

  private String commentAuthorEmail;

  private String commentAuthorUrl;

  private String commentAuthorAvatar;

  private String commentContent;

  private String commentAgent;

  private String commentIp;

  private Date commentCreateTime;

  /**
   * 角色(管理员1，访客0)
   */
  private Integer commentRole;

  /**
   * 非数据库字段
   */
  private Article article;
}
