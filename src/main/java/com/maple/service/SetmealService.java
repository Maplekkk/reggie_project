package com.maple.service;

import com.maple.dto.SetmealDto;
import com.maple.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.pojo.SetmealDish;
import com.sun.javafx.image.IntPixelGetter;

import java.util.List;

/**
* @author maple
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2023-01-15 15:23:24
*/
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    void deleteWithDish(List<Long> ids);
    void updateStatusById(Setmeal setmeal);
    SetmealDto getSetmealDishById(Long id);
}
