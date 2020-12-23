package com.wwpinkman.blog.entity;

import lombok.Data;

@Data
public class Menu {
  private Integer menuId;

  private String menuName;

  private String menuUrl;

  private Integer menuLevel;

  private String menuIcon;

  private Integer menuOrder;
}
