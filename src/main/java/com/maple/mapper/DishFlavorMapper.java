package com.maple.mapper;

import com.maple.pojo.DishFlavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author maple
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Mapper
* @createDate 2023-01-19 09:12:35
* @Entity com.maple.pojo.DishFlavor
*/
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
    List<DishFlavor> getAllByDishId(Long dishId);
}




