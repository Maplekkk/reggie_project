package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.maple.common.R;
import com.maple.dto.DishDto;
import com.maple.pojo.Category;
import com.maple.pojo.Dish;
import com.maple.service.DishFlavorService;
import com.maple.service.DishService;
import javafx.scene.shape.PathElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:DishController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-19
 * Version: v1.0
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private DishFlavorService dishFlavorService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("保存成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page<Dish> pageInfoDish = new Page<>(page, pageSize);
        Page<DishDto> pageInfo = new Page<>();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && name != "",Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfoDish, queryWrapper);
        if(page > pageInfoDish.getTotal())
        {
            page = (int) pageInfoDish.getPages();
            pageInfoDish.setCurrent(page);
            dishService.page(pageInfoDish, queryWrapper);
        }
        BeanUtils.copyProperties(pageInfoDish, pageInfo, "records");
        pageInfo.setRecords(dishService.getDishDtoWithPage(page, pageSize));
        return R.success(pageInfo);
    }

    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable("id") Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);
        return R.success("保存成功");
    }
    @GetMapping("/list")
    public R<List<DishDto>> list(Long categoryId){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId != null, Dish::getCategoryId, categoryId);
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        List<DishDto> res = list.stream().map(item->{
            DishDto dto = dishService.getByIdWithFlavor(item.getId());
            return dto;
        }).collect(Collectors.toList());
        return R.success(res);
    }
}
