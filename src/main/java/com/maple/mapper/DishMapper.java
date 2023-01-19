package com.maple.mapper;

import com.maple.dto.DishDto;
import com.maple.pojo.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author maple
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2023-01-15 15:23:17
* @Entity com.maple.pojo.Dish
*/
public interface DishMapper extends BaseMapper<Dish> {
    List<DishDto> getDishDtoWithPage(@Param("start") int start, @Param("pageSize") int pageSize);
}




