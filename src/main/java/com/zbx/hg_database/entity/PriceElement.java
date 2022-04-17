package com.zbx.hg_database.entity;

import lombok.Data;

/**
 * @日期 2022/4/17
 * @作者 zbx
 * @描述
 */
@Data
public class PriceElement {

    /**
     * 报价ID
     */
    private String id;

    /**
     * 部件名称
     */
    private String partName;

    /**
     * 材料
     */
    private String materialType;

    /**
     * 颜色
     */
    private String color;

}
