package com.maple.service;

import com.maple.dto.DishDto;
import com.maple.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author maple
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2023-01-15 15:23:17
*/
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    List<DishDto> getDishDtoWithPage(int current, int pageSize);

    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavor(DishDto dishDto);
}
