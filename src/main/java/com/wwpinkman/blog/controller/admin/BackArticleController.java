package com.wwpinkman.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.wwpinkman.blog.dto.ArticleParam;
import com.wwpinkman.blog.entity.Article;
import com.wwpinkman.blog.entity.Category;
import com.wwpinkman.blog.entity.Tag;
import com.wwpinkman.blog.entity.User;
import com.wwpinkman.blog.service.ArticleService;
import com.wwpinkman.blog.service.CategoryService;
import com.wwpinkman.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
  @Autowired
  private ArticleService articleService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private TagService tagService;

  @RequestMapping("")
  public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                      @RequestParam(required = false) String status, Model model){
    HashMap<String, Object> criteria = new HashMap<>(1);
    if (status == null){
      model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
    } else {
      criteria.put("status",status);
      model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
    }
    PageInfo<Article> articles = articleService.pageArticle(pageIndex, pageSize, criteria);
    model.addAttribute("pageInfo", articles);
    return "Admin/Article/index";
  }

  @RequestMapping("/insert")
  public String insertArticleView(Model model){
    List<Category> categoryList = categoryService.listCategory();
    List<Tag> tagList = tagService.listTag();
    model.addAttribute("categoryList",categoryList);
    model.addAttribute("tagList",tagList);
    return "Admin/Article/insert";
  }

  @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
  public String insertArticleSubmit(HttpSession session, ArticleParam articleParam) {
    Article article = new Article();

    User user = (User) session.getAttribute("user");
    if (user != null) {
      article.setArticleUserId(user.getUserId());
    }
    article.setArticleTitle(articleParam.getArticleTitle());

    int summaryLength = 120;
    String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
    if (summaryText.length() > summaryLength) {
      String summary = summaryText.substring(0, summaryLength);
      article.setArticleSummary(summary);
    } else {
      article.setArticleSummary(summaryText);
    }
    article.setArticleContent(articleParam.getArticleContent());
    article.setArticleStatus(articleParam.getArticleStatus());

    List<Category> categoryList = new ArrayList<>();
    if (articleParam.getArticleParentCategoryId() != null) {
      categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
    }
    if (articleParam.getArticleChildCategoryId() != null) {
      categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
    }
    article.setCategoryList(categoryList);

    List<Tag> tagList = new ArrayList<>();
    if (articleParam.getArticleTagIds() != null) {
      for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
        Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
        tagList.add(tag);
      }
    }
    article.setTagList(tagList);

    articleService.insertArticle(article);
    return "redirect:/admin/article";
  }

  @RequestMapping("/delete/{id}")
  public void deleteArticle(@PathVariable("id") Integer id) {
    articleService.deleteArticle(id);
  }

  @RequestMapping("/edit/{id}")
  public ModelAndView editArticleView(@PathVariable("id") Integer id){
    ModelAndView modelAndView = new ModelAndView();
    Article article = articleService.getArticleByStatusAndId(null, id);
    modelAndView.addObject("article",article);

    List<Category> categoryList = categoryService.listCategory();
    modelAndView.addObject("categoryList",categoryList);

    List<Tag> tagList = tagService.listTag();
    modelAndView.addObject("tagList",tagList);

    modelAndView.setViewName("Admin/Article/edit");
    return modelAndView;
  }

  /**
   * 编辑文章提交
   *
   * @param articleParam
   * @return
   */
  @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
  public String editArticleSubmit(ArticleParam articleParam) {
    Article article = new Article();
    article.setArticleId(articleParam.getArticleId());
    article.setArticleTitle(articleParam.getArticleTitle());
    article.setArticleContent(articleParam.getArticleContent());
    article.setArticleStatus(articleParam.getArticleStatus());
    //文章摘要
    int summaryLength = 120;
    String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
    if (summaryText.length() > summaryLength) {
      String summary = summaryText.substring(0, summaryLength);
      article.setArticleSummary(summary);
    } else {
      article.setArticleSummary(summaryText);
    }
    //填充分类
    List<Category> categoryList = new ArrayList<>();
    if (articleParam.getArticleParentCategoryId() != null) {
      categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
    }
    if (articleParam.getArticleChildCategoryId() != null) {
      categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
    }
    article.setCategoryList(categoryList);
    //填充标签
    List<Tag> tagList = new ArrayList<>();
    if (articleParam.getArticleTagIds() != null) {
      for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
        Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
        tagList.add(tag);
      }
    }
    article.setTagList(tagList);
    articleService.updateArticleDetail(article);
    return "redirect:/admin/article";
  }
}
