package com.zbx.hg_database.controller;

import com.zbx.hg_database.service.IPriceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
@RestController
@CrossOrigin
public class PriceController {

    IPriceService priceService;

    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/calcPrice")
    public String changePrice(String orderNo) {
        if (Objects.isNull(orderNo) || orderNo.equals("")) {
            return "订单号不能为空";
        }
        return priceService.changePriceByRule(orderNo);
    }

}
