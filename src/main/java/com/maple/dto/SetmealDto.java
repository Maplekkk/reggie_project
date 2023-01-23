package com.maple.dto;

import com.maple.pojo.Dish;
import com.maple.pojo.Setmeal;
import com.maple.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * ClassName:SetmealDto
 * Package: com.maple.dto
 * Description:
 * Author maple
 * Create 2023-01-22
 * Version: v1.0
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
