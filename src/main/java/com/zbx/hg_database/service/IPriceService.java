package com.zbx.hg_database.service;

import com.zbx.hg_database.entity.PriceRule;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
public interface IPriceService {

    String changePriceByRule(String orderNo);

    void changePriceRule(PriceRule rule);

    void refreshPriceRule();

    void deletePriceRule(PriceRule rule);
}
