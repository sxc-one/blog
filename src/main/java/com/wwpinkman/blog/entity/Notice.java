package com.wwpinkman.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
  private Integer noticeId;

  private String noticeTitle;

  private String noticeContent;

  private Date noticeCreateTime;

  private Date noticeUpdateTime;

  private Integer noticeStatus;

  private Integer noticeOrder;
}
