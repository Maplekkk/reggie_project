package com.maple.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maple.common.R;
import com.maple.pojo.Category;
import com.maple.service.CategoryService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:CategoryController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-15
 * Version: v1.0
 */

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category.toString());
        boolean save = categoryService.save(category);
        return save ? R.success("保存成功") : R.error("出现异常");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, queryWrapper);
        if(page > pageInfo.getPages())
        {
            pageInfo.setCurrent(pageInfo.getPages());
            categoryService.page(pageInfo, queryWrapper);
        }
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> deleteById(@RequestParam("ids") Long id){
        log.info("删除的id是:{}", id);
        boolean res = categoryService.remove(id);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> updateById(@RequestBody Category category){
        log.info("修改分类信息:{}", category.toString());
        boolean b = categoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> categoryList = categoryService.list(queryWrapper);
        return R.success(categoryList);
    }

}
