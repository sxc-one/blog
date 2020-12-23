package com.wwpinkman.blog.interceptor;

import com.wwpinkman.blog.entity.Article;
import com.wwpinkman.blog.entity.Category;
import com.wwpinkman.blog.entity.Menu;
import com.wwpinkman.blog.enums.ArticleStatus;
import com.wwpinkman.blog.enums.LinkStatus;
import com.wwpinkman.blog.service.*;
import com.wwpinkman.blog.entity.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class HomeResourceInterceptor implements HandlerInterceptor {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private MenuService menuService;

  @Autowired
  private TagService tagService;

  @Autowired
  private LinkService linkService;

  @Autowired
  private OptionsService optionsService;

  @Autowired
  private CategoryService categoryService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    List<Menu> menuList = menuService.listMenu();
    request.setAttribute("menuList",menuList);

    List<Category> categoryList = categoryService.listCategory();
    request.setAttribute("allCategoryList",categoryList);

    List<String> siteBasicStatistics = new ArrayList<String>();
    siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue()) + "");
    siteBasicStatistics.add(articleService.countArticleComment() + "");
    siteBasicStatistics.add(categoryService.countCategory() + "");
    siteBasicStatistics.add(tagService.countTag() + "");
    siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
    siteBasicStatistics.add(articleService.countArticleView() + "");
    request.setAttribute("siteBasicStatistics", siteBasicStatistics);

    Article lastUpdateArticle = articleService.getLastUpdateArticle();
    request.setAttribute("lastUpdateArticle",lastUpdateArticle);

    Options options = optionsService.getOptions();
    request.setAttribute("options", options);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }
}
