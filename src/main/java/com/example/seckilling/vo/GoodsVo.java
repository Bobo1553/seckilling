package com.example.seckilling.vo;

import com.example.seckilling.domain.Goods;

import java.sql.Date;

/**
 * @author Xiao Yijia
 * @create 2020/11/28 14:37
 */

public class GoodsVo extends Goods {

    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
