package com.zbx.hg_database.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zbx.hg_database.dao.IPriceDao;
import com.zbx.hg_database.entity.PriceElement;
import com.zbx.hg_database.entity.PriceRule;
import com.zbx.hg_database.service.IPriceService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
@Service
public class PriceServiceImpl implements IPriceService {

    private final IPriceDao priceDao;

    private List<PriceRule> ruleList;

    private final Set<String> curOrders = new HashSet<>();

    public PriceServiceImpl(IPriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Override
    public String changePriceByRule(String orderNo) {

        if (!priceDao.isInPriceGroup(orderNo)) {
            return "订单不在报价组";
        }

        List<PriceElement> list = priceDao.selectPriceDetail(orderNo);

        if (Objects.isNull(list) || list.size() == 0) {
            return "订单没有报价明细";
        }

        String msg;
        boolean b = curOrders.contains(orderNo);

        if (b) {
            msg =  "当前订单正在计算报价,请勿重复点击";
        }else {
            curOrders.add(orderNo);
            msg = matchPriceRule(list);
            curOrders.remove(orderNo);
        }

        return msg;
    }

    /**
     * 修改或者新增报价规则
     * @param rule rule
     */
    @Override
    public void changePriceRule(PriceRule rule) {
        boolean b = priceDao.isRuleExists(rule);
        if (b) {
            priceDao.updatePriceRule(rule);
        }else {
            priceDao.insertPriceRule(rule);
        }
    }

    /**
     * 刷新报价规则
     */
    @Override
    public void refreshPriceRule() {
        this.ruleList = priceDao.selectPriceRule();
    }

    /**
     * 删除报价规则
     * @param rule rule
     */
    @Override
    public void deletePriceRule(PriceRule rule) {
        priceDao.deletePriceRule(rule);
    }

    /**
     * 报价明细匹配报价规则
     * @param list 报价明细列表
     * @return ok
     */
    private String matchPriceRule(List<PriceElement> list) {

        if (Objects.isNull(this.ruleList)) {
            this.ruleList = priceDao.selectPriceRule();
        }

        if (ruleList.size() == 0) {
            return "当前没有任何匹配规则";
        }

        boolean a,b,c;
        Map<String, Double> changeList = new HashMap<>();

        for (PriceElement item : list) {
            if (Objects.isNull(item.getPartName())) {
                item.setPartName("");
            }
            if (Objects.isNull(item.getMaterialType())) {
                item.setMaterialType("");
            }
            if (Objects.isNull(item.getColor())) {
                item.setColor("");
            }
            for (PriceRule rule : ruleList) {
                a = StrUtil.equals(item.getPartName().trim(),rule.getPartName().trim()) || StrUtil.equals("*",rule.getPartName());
                b =  StrUtil.equals(item.getMaterialType().trim(),rule.getMaterialType().trim()) || StrUtil.equals("*",rule.getMaterialType());
                c =  StrUtil.equals(item.getColor().trim(),rule.getColor().trim()) || StrUtil.equals("*",rule.getColor());
                if (a && b && c) {
                    changeList.put(item.getId(),rule.getDiscount());
                    break;
                }
            }
        }

        return "匹配完成,修改" + changeList.size() + "条";
    }



}
