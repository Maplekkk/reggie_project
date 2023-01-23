package com.maple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.dto.SetmealDto;
import com.maple.exception.CustomException;
import com.maple.pojo.Setmeal;
import com.maple.pojo.SetmealDish;
import com.maple.service.SetmealDishService;
import com.maple.service.SetmealService;
import com.maple.mapper.SetmealMapper;
import jdk.nashorn.internal.runtime.regexp.joni.WarnCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author maple
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2023-01-15 15:23:24
*/
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{
    @Resource
    private SetmealDishService setmealDish;
    @Resource
    private SetmealMapper setmealMapper;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map(item->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDish.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void deleteWithDish(List<Long> ids) {

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        long count = this.count(queryWrapper);
        if(count > 0)
        {
            throw new CustomException("套餐正在售卖中");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SetmealDish::getSetmealId, ids);
        setmealDish.remove(wrapper);
    }

    @Override
    public void updateStatusById(Setmeal setmeal) {
        this.updateById(setmeal);
    }

    @Override
    public SetmealDto getSetmealDishById(Long id) {
        return setmealMapper.getSetmealDtoById(id);
    }
}




