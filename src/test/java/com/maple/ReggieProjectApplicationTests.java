package com.maple;

import com.maple.dto.DishDto;
import com.maple.pojo.DishFlavor;
import com.maple.service.DishFlavorService;
import com.maple.service.DishService;
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
    @Test
    void contextLoads() {
        List<DishDto> dishDtoWithPage = dishService.getDishDtoWithPage(1, 5);
        dishDtoWithPage.forEach(System.out::println);
    }


}
