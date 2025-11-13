package com.lanxige.goods.vo;

import lombok.Data;

@Data
public class SpuSearch {
    private String name;
    private Integer brandId;
    private Integer page;
    private Integer pageSize;
    private Integer category1Id;
    private Integer category2Id;
    private Integer category3Id;
    private Integer status;
    private Integer isMarketable;
}
