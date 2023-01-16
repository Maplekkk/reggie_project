package com.maple.service;

import com.maple.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author maple
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2023-01-15 14:39:13
*/
public interface CategoryService extends IService<Category> {
    boolean remove(Long id);
}
