package com.wwpinkman.blog.service.impl;

import com.wwpinkman.blog.entity.Menu;
import com.wwpinkman.blog.mapper.MenuMapper;
import com.wwpinkman.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuMapper menuMapper;

  @Override
  public List<Menu> listMenu() {
    List<Menu> menuList = menuMapper.listMenu();
    return menuList;
  }

  @Override
  public void insertMenu(Menu menu) {
    menuMapper.insert(menu);
  }

  @Override
  public void deleteMenu(Integer id) {
    menuMapper.deleteById(id);
  }

  @Override
  public void updateMenu(Menu menu) {
    menuMapper.update(menu);
  }

  @Override
  public Menu getMenuById(Integer id) {
    return menuMapper.getMenuById(id);
  }
}
