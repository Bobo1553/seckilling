package com.example.seckilling.dao;

import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xiao Yijia
 * @create 2020/11/28 14:36
 */

@Mapper
@Component
public interface GoodsDao {

    @Select("select goods.*, sg.stock_count, sg.start_date, sg.end_date from seckilling_goods sg left join goods on sg.goods_id = goods.id")
    List<GoodsVo> listGoodsVo();

}
