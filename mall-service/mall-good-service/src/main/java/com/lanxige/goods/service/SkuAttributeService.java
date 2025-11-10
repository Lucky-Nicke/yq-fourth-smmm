package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.model.SkuAttribute;
import com.lanxige.vo.SkuAttributeSearch;

import java.util.List;


public interface SkuAttributeService extends IService<SkuAttribute> {
    List<SkuAttribute> queryByCategoryId(Integer categoryId);
    Page<SkuAttribute> queryPageList(Integer page, Integer size, SkuAttributeSearch skuAttributeSearch);
    Integer addSkuIdToCategory(SkuAttribute skuAttribute);
}
