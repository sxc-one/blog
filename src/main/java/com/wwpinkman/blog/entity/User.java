package com.wwpinkman.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sxc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
//  private static final long serialVersionUID = -4415517704211731385L;
  private Integer userId;

  private String userName;

  private String userPass;

  private String userNickname;

  private String userEmail;

  private String userUrl;

  private String userAvatar;

  private String userLastLoginIp;

  private Date userRegisterTime;

  private Date userLastLoginTime;

  private Integer userStatus;

  /**
   * 文章数量（不是数据库字段）
   */
  private Integer articleCount;

}