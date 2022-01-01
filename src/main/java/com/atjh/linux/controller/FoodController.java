package com.atjh.linux.controller;


import com.atjh.linux.entity.Food;
import com.atjh.linux.entity.vo.FoodQuery;
import com.atjh.linux.mapper.FoodMapper;
import com.atjh.linux.service.FoodService;
import com.atjh.linux.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
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

    @Autowired
    private FoodMapper foodMapper;

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
        String kind = foodQuery.getKind();

        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id",id);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(kind)) {
            wrapper.like("kind",kind);
        }
        Page<Food> page = new Page<>(current,limit);
        foodService.page(page,wrapper);
        List<Food> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list",records).data("total",total);
    }

    @ApiOperation(value = "菜品加入订单,is_pick改为1")
    @PutMapping("{id}")
    public R ding(@PathVariable String id){
        Food food1 = foodService.getById(id);
        String isPick = food1.getIsPick();
        if(isPick=="1"){
            return R.error();
        }else {
            food1.setIsPick("1");
            foodMapper.updateById(food1);
            return R.ok();
        }

//        foodService.updatePick(id);
//        Boolean isPick = food1.getIsPick();
//        System.out.println("sssssssssssssssssssssssssss"+isPick);
//
//            food1.setIsPick(!isPick);
//
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+isPick);



    }

    @ApiOperation(value = "查看订单,查看所有is_pick为1的")
    @GetMapping("getAllPicked")
    public R getAllPicked() {
        QueryWrapper<Food> wrapper=new QueryWrapper<>();
        wrapper.eq("is_pick",1);
//
//        if(food.getIsPick()=="1"){
//            List<Food> foodPickedList = foodService.list();
//            System.out.println(foodPickedList);
//            return R.ok().data("foodPickedList",foodPickedList);
//        }else {
//            return R.error();
//        }

        List<Food> foodPickedList = foodService.list(wrapper);
            System.out.println(foodPickedList);

        return R.ok().data("foodPickedList",foodPickedList);
    }
}

