package com.maple.dto;

import com.maple.pojo.Dish;
import com.maple.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:DishDto
 * Package: com.maple.dto
 * Description:
 * Author maple
 * Create 2023-01-19
 * Version: v1.0
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
