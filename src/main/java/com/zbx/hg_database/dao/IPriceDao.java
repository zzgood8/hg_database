package com.zbx.hg_database.dao;

import com.zbx.hg_database.entity.PriceElement;
import com.zbx.hg_database.entity.PriceRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
@Mapper
public interface IPriceDao {

    /**
     * 判断订单是否在报价组
     * @param orderNo 订单号
     * @return 是否存在
     */
    boolean isInPriceGroup(String orderNo);

    /**
     * 获取订单的报价明细
     * @param orderNo 订单号
     * @return 报价明细列表
     */
    List<PriceElement> selectPriceDetail(String orderNo);

    /**
     * 获取所有的报价规则
     * @return 报价规则列表
     */
    List<PriceRule> selectPriceRule();

    /**
     * 判断规则是否存在
     * @param rule 报价规则
     * @return 是否存在
     */
    boolean isRuleExists(PriceRule rule);

    /**
     * 插入报价规则
     * @param rule 报价规则
     */
    void insertPriceRule(PriceRule rule);

    /**
     * 更新报价规则
     * @param rule 报价规则
     */
    void updatePriceRule(PriceRule rule);

    /**
     * 删除报价规则
     * @param rule 报价规则
     */
    void deletePriceRule(PriceRule rule);

}
