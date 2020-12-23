package com.wwpinkman.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryRef {

  private Integer articleId;

  private Integer categoryId;
}
