package com.example.seckilling.controller;

import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.result.Result;
import com.example.seckilling.service.SeckillingUserService;
import com.example.seckilling.util.MD5Util;
import com.example.seckilling.util.ValidatorUtil;
import com.example.seckilling.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result<Boolean> doLogin(LoginVo loginVo) {
        LOG.info(loginVo.toString());
        String formPass = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(formPass)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        // 登录
        CodeMsg codeMsg = seckillingUserService.login(loginVo);
        if (codeMsg.getCode() == 0) {
            return Result.success(true);
        } else {
            return Result.error(codeMsg);
        }
    }

}
