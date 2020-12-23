package com.wwpinkman.blog.mapper;

import com.wwpinkman.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

  int deleteById(Integer userId);

  int insert(User user);

  User getUserById(Integer userId);

  int update(User user);

  List<User> listUser();

  User getUserByNameOrEmail(String str);

  User getUserByName(String name);

  User getUserByEmail(String email);
}
