package com.example.seckilling.controller;

import com.example.seckilling.domain.SeckillingUser;
import com.example.seckilling.service.GoodsService;
import com.example.seckilling.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Xiao Yijia
 * @create 2020/9/18 10:02
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping("/to_list")
    public String toList(Model model, SeckillingUser user) {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

}
