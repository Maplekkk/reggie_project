package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.pojo.Dish;
import com.maple.service.DishService;
import com.maple.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author maple
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2023-01-15 15:23:17
*/
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




