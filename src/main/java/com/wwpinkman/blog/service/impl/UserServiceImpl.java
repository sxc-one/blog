package com.wwpinkman.blog.service.impl;

import com.wwpinkman.blog.entity.User;
import com.wwpinkman.blog.mapper.ArticleMapper;
import com.wwpinkman.blog.mapper.UserMapper;
import com.wwpinkman.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ArticleMapper articleMapper;

  @Override
  public List<User> listUser() {
    List<User> userList = userMapper.listUser();
    for (int i = 0; i < userList.size(); i++) {
      Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
      userList.get(i).setArticleCount(articleCount);
    }
    return userList;
  }

  @Override
  public User getUserById(Integer id) {
    return userMapper.getUserById(id);
  }

  @Override
  public void updateUser(User user) {
    userMapper.update(user);
  }

  @Override
  public void deleteUser(Integer id) {
    userMapper.deleteById(id);
  }

  @Override
  public int insert(User user) {
    user.setUserRegisterTime(new Date());
    int cnt = userMapper.insert(user);
    return cnt;
  }

  @Override
  public User getUserByNameOrEmail(String str) {
    return userMapper.getUserByNameOrEmail(str);
  }

  @Override
  public User getUserByName(String name) {
    return userMapper.getUserByName(name);
  }

  @Override
  public User getUserByEmail(String email) {
    return userMapper.getUserByEmail(email);
  }
}
