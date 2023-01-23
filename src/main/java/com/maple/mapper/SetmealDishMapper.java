package com.maple.mapper;

import com.maple.pojo.SetmealDish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author maple
* @description 针对表【setmeal_dish(套餐菜品关系)】的数据库操作Mapper
* @createDate 2023-01-22 15:39:40
* @Entity com.maple.pojo.SetmealDish
*/
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
    SetmealDish getByIdSetmealDish(Long id);
}




