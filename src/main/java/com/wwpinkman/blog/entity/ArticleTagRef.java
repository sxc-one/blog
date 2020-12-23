package com.wwpinkman.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagRef {

  private Integer articleId;

  private Integer tagId;

}
