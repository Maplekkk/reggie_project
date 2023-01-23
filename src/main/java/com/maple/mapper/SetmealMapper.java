package com.maple.mapper;

import com.maple.dto.SetmealDto;
import com.maple.pojo.Setmeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author maple
* @description 针对表【setmeal(套餐)】的数据库操作Mapper
* @createDate 2023-01-15 15:23:24
* @Entity com.maple.pojo.Setmeal
*/
public interface SetmealMapper extends BaseMapper<Setmeal> {
    SetmealDto getSetmealDtoById(Long id);
}




