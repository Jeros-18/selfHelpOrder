package com.atjh.linux.service.impl;

import com.atjh.linux.entity.Food;
import com.atjh.linux.mapper.FoodMapper;
import com.atjh.linux.service.FoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jahui
 * @since 2021-12-27
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public void updatePick(String id) {
        foodMapper.updatePick(id);
    }
}
