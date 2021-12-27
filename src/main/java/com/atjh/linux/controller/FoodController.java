package com.atjh.linux.controller;


import com.atjh.linux.entity.Food;
import com.atjh.linux.entity.vo.FoodQuery;
import com.atjh.linux.service.FoodService;
import com.atjh.linux.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jahui
 * @since 2021-12-27
 */
@Api
@RestController
@RequestMapping("/linux/food")
@CrossOrigin
public class FoodController {
    @Autowired
    private FoodService foodService;

    @ApiOperation(value = "所有菜品列表")
    @GetMapping
    public R getAllFood(){
        List<Food> foodList = foodService.list(null);
        return R.ok().data("foodList",foodList);
    }

    @ApiOperation(value = "带条件分页查询")
    @PostMapping("getFoodPageVo/{current}/{limit}")
    public R getFoodPageVo(@PathVariable Long current,
                           @PathVariable Long limit,
                           @RequestBody FoodQuery foodQuery){
        Integer id = foodQuery.getId();
        String name = foodQuery.getName();

        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id",id);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("name",name);
        }
        Page<Food> page = new Page<>();
        foodService.page(page,wrapper);
        List<Food> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }
}

