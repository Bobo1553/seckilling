package com.example.seckilling.controller;

import com.example.seckilling.domain.User;
import com.example.seckilling.redis.RedisService;
import com.example.seckilling.redis.UserKey;
import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.result.Result;
import com.example.seckilling.service.UserService;
import com.example.seckilling.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xiao Yijia
 * @create 2020-09-12 10:52
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo){
        System.out.println(loginVo);
        LOG.info(loginVo.toString());
        return null;
    }


}
