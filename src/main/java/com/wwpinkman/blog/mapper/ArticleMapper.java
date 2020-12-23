package com.wwpinkman.blog.mapper;

import com.wwpinkman.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ArticleMapper {

  Integer insert(Article article);

  Integer deleteById(Integer articleId);

  Integer update(Article article);

  List<Article> findAll(HashMap<String,Object> criteria);

  List<Article> listAllNotWithContent();

  Integer countArticle(@Param(value = "status") Integer status);

  Integer countArticleComment();

  Integer countArticleView();

  List<Article> listArticle();

  Article getArticleByStatusAndId(@Param("status") Integer status,@Param("id") Integer id);

  List<Article> pageArticle(@Param("status") Integer status,
                            @Param("pageIndex") Integer pageIndex,
                            @Param("pageSize") Integer pageSize);

  List<Article> listArticleByViewCount(@Param("limit") Integer limit);

  Article getAfterArticle(@Param("id") Integer id);

  Article getPreArticle(@Param("id") Integer id);

  List<Article> listRandomArticle(@Param("limit") Integer limit);

  List<Article> listArticleByCommentCount(@Param("limit") Integer limit);

  void updateCommentCount(@Param("articleId") Integer articleId);

  Article getLastUpdateArticle();

  Integer countArticleByUser(@Param("id") Integer id);

  /**
   * 根据分类ID
   *
   * @param categoryId 分类ID
   * @param limit      查询数量
   * @return 文章列表
   */
  List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                        @Param("limit") Integer limit);

  /**
   * 根据分类ID
   *
   * @param categoryIds 分类ID集合
   * @param limit       查询数量
   * @return 文章列表
   */
  List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                         @Param("limit") Integer limit);

  List<Article> listArticleByLimit(Integer limit);

  Integer deleteBatch(@Param("ids") List<Integer> ids);
}
