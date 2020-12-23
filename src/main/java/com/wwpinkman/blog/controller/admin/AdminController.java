package com.wwpinkman.blog.controller.admin;

import com.wwpinkman.blog.entity.Article;
import com.wwpinkman.blog.entity.Comment;
import com.wwpinkman.blog.entity.User;
import com.wwpinkman.blog.service.ArticleService;
import com.wwpinkman.blog.service.CommentService;
import com.wwpinkman.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.wwpinkman.blog.util.MyUtils.getIpAddr;

/**
 * author:sxc
 */
@Controller
public class AdminController {
  @Autowired
  private UserService userService;

  @Autowired
  private ArticleService articleService;

  @Autowired
  private CommentService commentService;
  /**
   * 后台首页
   * @return
   */
  @RequestMapping("/admin")
  public String index(Model model){
    List<Article> articleList = articleService.listRecentArticle(5);
    model.addAttribute("articleList",articleList);

    List<Comment> commentList = commentService.listRecentComment(5);
    model.addAttribute("commentList", commentList);
    return "Admin/index";
  }

  /**
   * 登录页面
   * @return
   */
  @RequestMapping("/login")
  public String loginPage(){
    return "Admin/login";
  }

  /**
   * 登录验证
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "loginVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
  @ResponseBody
  public String loginVerify(HttpServletRequest request, HttpServletResponse response){
    HashMap<String, Object> map = new HashMap<>();

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String rememberme = request.getParameter("rememberme");

//    User user = new User();
//    user.setUserName("admin");
//    user.setUserPass("123456");
    User user = userService.getUserByNameOrEmail(username);

    if (user == null){
      map.put("code", 0);
      map.put("msg", "用户名无效！");
    }else if (!user.getUserPass().equals(password)){
      map.put("code", 0);
      map.put("msg", "密码错误！");
    }else {
      map.put("code", 1);
      map.put("msg", "");

      request.getSession().setAttribute("user",user);

      if (rememberme != null){
        Cookie nameCookie = new Cookie("username", username);
        nameCookie.setMaxAge(60 * 60 * 24 * 3);
        Cookie pwdCookie = new Cookie("password", password);
        pwdCookie.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(nameCookie);
        response.addCookie(pwdCookie);
      }
      user.setUserLastLoginTime(new Date());
      user.setUserLastLoginIp(getIpAddr(request));
      userService.updateUser(user);
    }
    String result = new JSONObject(map).toString();
    return result;
  }

  @RequestMapping("/admin/logout")
  public String logout(HttpSession session){
    session.removeAttribute("user");
    session.invalidate();
    return "redirect:/login";
  }



}
