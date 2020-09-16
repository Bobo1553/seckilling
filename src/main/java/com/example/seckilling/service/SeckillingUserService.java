package com.example.seckilling.service;

import com.example.seckilling.dao.SeckillingUserDao;
import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.util.MD5Util;
import com.example.seckilling.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Yijia
 * @create 2020/9/16 21:50
 */

@Service
public class SeckillingUserService {

    private static final Logger LOG = LoggerFactory.getLogger(SeckillingUserService.class);

    private final SeckillingUserDao seckillingUserDao;

    public SeckillingUserService(SeckillingUserDao seckillingUserDao) {
        this.seckillingUserDao = seckillingUserDao;
    }

    public SeckillingUser getById(long id) {
        return seckillingUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null)
            return CodeMsg.SERVER_ERROR;
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        SeckillingUser seckillingUser = getById(Long.parseLong(mobile));
        if (seckillingUser == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        // 验证密码
        String dbPass = seckillingUser.getPassword();
        String dbSalt = seckillingUser.getSalt();
        String calculatePass = MD5Util.formPassToDbPass(formPass, dbSalt);
        LOG.info("dbPass:" + dbPass);
        LOG.info("calculatePass:" + calculatePass);
        if (!calculatePass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }

}
