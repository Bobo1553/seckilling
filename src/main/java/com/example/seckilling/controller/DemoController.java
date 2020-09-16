package com.example.seckilling.controller;

import com.example.seckilling.domain.User;
import com.example.seckilling.redis.RedisService;
import com.example.seckilling.redis.UserKey;
import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.result.Result;
import com.example.seckilling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xiao Yijia
 * @create 2020-09-12 10:52
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

    private final UserService userService;
    private final RedisService redisService;

    public DemoController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello, imooc!");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "ethan");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/transaction")
    @ResponseBody
    public Result<Boolean> dbTransaction() {
        userService.transaction();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getByID, "1", User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("ethan");
        boolean result = redisService.set(UserKey.getByID, "1", user);
        return Result.success(result);
    }

}
