package com.example.seckilling.service;

import com.example.seckilling.dao.GoodsDao;
import com.example.seckilling.domain.Goods;
import com.example.seckilling.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xiao Yijia
 * @create 2020/11/28 14:26
 */

@Service
public class GoodsService {

    private final GoodsDao goodsDao;

    public GoodsService(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }
}
