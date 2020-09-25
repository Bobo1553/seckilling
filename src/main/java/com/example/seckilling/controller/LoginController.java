package com.example.seckilling.controller;

import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.result.Result;
import com.example.seckilling.service.SeckillingUserService;
import com.example.seckilling.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Xiao Yijia
 * @create 2020-09-12 10:52
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private final SeckillingUserService seckillingUserService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    public LoginController(SeckillingUserService seckillingUserService) {
        this.seckillingUserService = seckillingUserService;
    }

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        LOG.info(loginVo.toString());
        // 登录
        seckillingUserService.login(response, loginVo);
        return Result.success(true);
    }

}
