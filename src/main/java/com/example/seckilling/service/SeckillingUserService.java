package com.example.seckilling.service;

import com.example.seckilling.dao.SeckillingUserDao;
import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.exception.GlobalException;
import com.example.seckilling.redis.RedisService;
import com.example.seckilling.redis.SeckillingUserKey;
import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.util.MD5Util;
import com.example.seckilling.util.UUIDUtil;
import com.example.seckilling.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xiao Yijia
 * @create 2020/9/16 21:50
 */

@Service
public class SeckillingUserService {

    public static final String COOKIE_NAME_TOKEN = "token";
    private static final Logger LOG = LoggerFactory.getLogger(SeckillingUserService.class);

    private final RedisService redisService;

    private final SeckillingUserDao seckillingUserDao;

    public SeckillingUserService(SeckillingUserDao seckillingUserDao, RedisService redisService) {
        this.seckillingUserDao = seckillingUserDao;
        this.redisService = redisService;
    }

    public SeckillingUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillingUser user = redisService.get(SeckillingUserKey.token, token, SeckillingUser.class);
        if (user != null) {
            addCookie(response, user);
        }
        return user;
    }

    public SeckillingUser getById(long id) {
        return seckillingUserDao.getById(id);
    }

    public void login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        SeckillingUser seckillingUser = getById(Long.parseLong(mobile));
        if (seckillingUser == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        // 验证密码
        String dbPass = seckillingUser.getPassword();
        String dbSalt = seckillingUser.getSalt();
        String calculatePass = MD5Util.formPassToDbPass(formPass, dbSalt);
        LOG.debug("dbPass:" + dbPass);
        LOG.debug("calculatePass:" + calculatePass);
        if (!calculatePass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        // 生成cookie
        addCookie(response, seckillingUser);
    }

    private void addCookie(HttpServletResponse response, SeckillingUser user) {
        String token = UUIDUtil.uuid();
        redisService.set(SeckillingUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SeckillingUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
