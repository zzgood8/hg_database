package com.zbx.hg_database.controller;

import com.zbx.hg_database.entity.PriceRule;
import com.zbx.hg_database.service.IPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
@RestController
public class PriceRuleController {

    IPriceService priceService;

    public PriceRuleController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/refreshRule")
    public String refreshRule() {
        priceService.refreshPriceRule();
        return "ok";
    }

    @PostMapping("/changeRule")
    public String changeRule(PriceRule rule) {
        String vailt = isRuleNull(rule);
        if (vailt != null) {
            return vailt;
        }
        priceService.changePriceRule(rule);
        return "ok";
    }

    @GetMapping("/deleteRule")
    public String deleteRule(PriceRule rule) {
        String vailt = isRuleNull(rule);
        if (vailt != null) {
            return vailt;
        }
        priceService.deletePriceRule(rule);
        return "ok";
    }

    private String isRuleNull(PriceRule rule) {
        if (Objects.isNull(rule.getPartName()) || rule.getPartName().equals("")) {
            return "部件名称规则不能为空！";
        }

        if (Objects.isNull(rule.getMaterialType()) || rule.getMaterialType().equals("")) {
            return "材料规则不能为空！";
        }

        if (Objects.isNull(rule.getColor()) || rule.getColor().equals("")) {
            return "颜色规则不能为空！";
        }

        if (Objects.isNull(rule.getDiscount())) {
            return "折扣规则不能为空！";
        }

        return null;
    }
}
