package com.example.seckilling.controller;

import com.example.seckilling.domain.SeckillingUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiao Yijia
 * @create 2020/9/18 10:02
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String toList(Model model, SeckillingUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }

}
