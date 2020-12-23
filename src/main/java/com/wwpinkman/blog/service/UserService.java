package com.wwpinkman.blog.service;

import com.wwpinkman.blog.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

  void deleteUser(Integer id);

  int insert(User user);

  User getUserById(Integer id);

  void updateUser(User user);

  List<User> listUser();

  User getUserByNameOrEmail(String str);

  User getUserByName(String name);

  User getUserByEmail(String email);
}
