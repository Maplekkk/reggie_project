package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.dto.SetmealDto;
import com.maple.pojo.Dish;
import com.maple.pojo.SetmealDish;
import com.maple.service.DishService;
import com.maple.service.SetmealDishService;
import com.maple.mapper.SetmealDishMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author maple
* @description 针对表【setmeal_dish(套餐菜品关系)】的数据库操作Service实现
* @createDate 2023-01-22 15:39:40
*/
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish>
    implements SetmealDishService{


}




