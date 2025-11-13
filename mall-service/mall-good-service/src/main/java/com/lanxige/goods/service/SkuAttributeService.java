package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.goods.model.SkuAttribute;
import com.lanxige.goods.vo.SkuAttributeSearch;

import java.util.List;


public interface SkuAttributeService extends IService<SkuAttribute> {
    List<SkuAttribute> queryByCategoryId(Integer categoryId);
    Page<SkuAttribute> queryPageList(Integer page, Integer size, SkuAttributeSearch skuAttributeSearch);
    Integer addSkuIdToCategory(SkuAttribute skuAttribute);
}
