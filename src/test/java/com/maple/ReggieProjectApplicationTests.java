package com.maple;

import com.maple.dto.DishDto;
import com.maple.dto.SetmealDto;
import com.maple.mapper.SetmealMapper;
import com.maple.pojo.DishFlavor;
import com.maple.service.DishFlavorService;
import com.maple.service.DishService;
import com.maple.service.SetmealService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest(classes = ReggieProjectApplication.class)
class ReggieProjectApplicationTests {

    @Resource
    DishFlavorService dishFlavorService;
    @Resource
    DishService dishService;

    @Resource
    SetmealMapper setmealMapper;
    @Test
    void contextLoads() {
        SetmealDto setmealDtoById = setmealMapper.getSetmealDtoById(1415580119015145474L);
        System.out.println(setmealDtoById.getSetmealDishes());
    }


}
