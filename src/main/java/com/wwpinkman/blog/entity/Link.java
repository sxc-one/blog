package com.wwpinkman.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Link {
  private Integer linkId;

  private String linkUrl;

  private String linkName;

  private String linkImage;

  private String linkDescription;

  private String linkOwnerNickname;

  private String linkOwnerContact;

  private Date linkUpdateTime;

  private Date linkCreateTime;

  private Integer linkOrder;

  private Integer linkStatus;
}
