package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.pojo.DishFlavor;
import com.maple.service.DishFlavorService;
import com.maple.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author maple
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2023-01-19 09:12:35
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

    @Resource
    private DishFlavorMapper dishFlavorMapper;
    @Override
    public List<DishFlavor> getDishFlavorsById(Long id) {
        List<DishFlavor> allByDishId = dishFlavorMapper.getAllByDishId(id);
        return allByDishId;
    }
}




