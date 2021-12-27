package com.atjh.linux.api;


import com.atjh.linux.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "模拟登陆")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginInController {
    @ApiOperation(value = "登陆")
    @PostMapping("login")
    //{"code":20000,"data":{"token":"admin"}}
    public R login(){
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("info")
    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    public R info(){
        return R.ok().data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }




}
