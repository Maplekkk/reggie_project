package com.maple.service;

import com.maple.pojo.DishFlavor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author maple
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service
* @createDate 2023-01-19 09:12:35
*/
public interface DishFlavorService extends IService<DishFlavor> {
    List<DishFlavor> getDishFlavorsById(Long id);
}
