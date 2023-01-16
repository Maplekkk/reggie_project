package com.maple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.exception.CustomException;
import com.maple.mapper.DishMapper;
import com.maple.pojo.Category;
import com.maple.pojo.Dish;
import com.maple.pojo.Setmeal;
import com.maple.service.CategoryService;
import com.maple.mapper.CategoryMapper;
import com.maple.service.DishService;
import com.maple.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author maple
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2023-01-15 14:39:13
*/
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Resource
    private DishService dishService;
    @Resource
    private SetmealService setmealService;

    @Override
    public boolean remove(Long id) {
        // 关联菜品
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId, id);
        int cnt = (int) dishService.count(queryWrapper);
        // 关联套餐
        if(cnt > 0)
        {
            throw new CustomException("已经关联了菜品, 不能删除");
        }
        LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Setmeal::getCategoryId, id);
        cnt += (int) setmealService.count(queryWrapper1);
        if(cnt > 0)
        {
            throw new CustomException("已经关联了套餐, 不能删除");
        }
        return super.removeById(id);
    }
}




