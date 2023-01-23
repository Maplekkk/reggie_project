package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.common.R;
import com.maple.dto.SetmealDto;
import com.maple.pojo.Category;
import com.maple.pojo.Setmeal;
import com.maple.pojo.SetmealDish;
import com.maple.service.CategoryService;
import com.maple.service.SetmealDishService;
import com.maple.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:SetmealController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-22
 * Version: v1.0
 */

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Resource
    private SetmealService setmealService;
    @Resource
    private SetmealDishService  setmealDishService;
    @Resource
    private CategoryService categoryService;


    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("添加成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page<Setmeal> pageInfo = new Page<Setmeal>();
        Page<SetmealDto> dtoPage = new Page<SetmealDto>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null && name != "",Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map(item->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Category byId = categoryService.getById(item.getCategoryId());
            if(byId != null) {
                setmealDto.setCategoryName(byId.getName());
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }


    @DeleteMapping
     public R<String> delete(@RequestParam List<Long> ids){
        setmealService.deleteWithDish(ids);
        return R.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable("status") Integer status, @RequestParam("ids") Long ids){
        System.out.println(status);
        System.out.println(ids);
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        setmeal.setId(ids);
        setmealService.updateStatusById(setmeal);
        return R.success("状态改变成功");
    }

    @GetMapping("/{id}")
    public R<SetmealDto> getSetMealInfo(@PathVariable Long id){
        SetmealDto dto = setmealService.getSetmealDishById(id);
        return R.success(dto);
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        Long categoryId = setmeal.getCategoryId();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId!= null,Setmeal::getCategoryId, categoryId);
        queryWrapper.eq(setmeal.getStatus()!= null,Setmeal::getStatus, setmeal.getStatus());
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);
    }
}
